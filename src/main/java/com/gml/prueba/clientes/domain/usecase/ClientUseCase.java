package com.gml.prueba.clientes.domain.usecase;

import com.gml.prueba.clientes.domain.api.IClientServicePort;
import com.gml.prueba.clientes.domain.model.Client;
import com.gml.prueba.clientes.domain.spi.IClientPersistencePort;
import com.gml.prueba.clientes.domain.validation.ClientValidation;
import com.gml.prueba.clientes.infrastructure.configuration.constant.ClientConstant;
import com.gml.prueba.clientes.infrastructure.configuration.enums.StateEnum;
import com.gml.prueba.clientes.infrastructure.exception.BadRequestException;
import com.gml.prueba.clientes.infrastructure.exception.NoDataFoundException;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

public class ClientUseCase implements IClientServicePort {

    private static final Logger logger = Logger.getLogger(ClientUseCase.class.getName());

    private final IClientPersistencePort clientPersistencePort;
    private ClientValidation clientValidation = new ClientValidation();

    public ClientUseCase(IClientPersistencePort clientPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
    }

    @Override
    public void saveClient(Client client) {

        logger.info("START: saveClient ----> "+client.toString());

        if(validateClient(client)) {
            client.setSharedKey(generateSharedKey(client.getEmail()));
            client.setStateClient(StateEnum.STATE_ACT.getState());
            client.setCreateDate(LocalDate.parse(generateFormatDate(LocalDate.now())));

            clientPersistencePort.saveClient(client);
        }else {
            throw new BadRequestException();
        }
        logger.info("END: saveClient ----> "+client.toString());
    }

    @Override
    public Client getClient(String sharedKey) {
        logger.info("START: getClient ----> "+sharedKey);
        if(clientValidation.validateSharedKey(sharedKey)) {
            logger.info("END: getClient");
            return clientPersistencePort.getClientBySharedKey(sharedKey.toUpperCase().trim());
        }else {
            throw new BadRequestException();
        }
    }

    /*
    @Override
    public List<Client> getClientWithFilters(String name, String phone, String email, String startDate
            , String endDate) {
        logger.info("START: getClientWithFilters");
        if (validateFilters((name==null)?"":name, (phone==null)?"":phone, (email==null)?"":email
                , (startDate==null)?"":startDate, (endDate==null)?"":endDate)){

            return clientPersistencePort.getClientWithFilters((name==null)?"":name, (phone==null)?"":phone
                    , (email==null)?"":email, (startDate==null)?"":startDate, (endDate==null)?"":endDate);
        }
        return clientPersistencePort.getAllClient();
    } */

    @Override
    public List<Client> getClientWithFilters(String name, String phone, String email, String startDate
            , String endDate) {
        logger.info("START: getClientWithFilters");
        if (validateFilters(validateNoNEmpty(name), validateNoNEmpty(phone), validateNoNEmpty(email)
                , validateNoNEmpty(startDate), validateNoNEmpty(endDate))){

            return clientPersistencePort.getClientWithFilters(validateNoNEmpty(name), validateNoNEmpty(phone)
                    , validateNoNEmpty(email), validateNoNEmpty(startDate), validateNoNEmpty(endDate));
        }
        return clientPersistencePort.getAllClient();
    }

    @Override
    public List<Client> getAllClient() {
        logger.info("START: getAllClient");
        return clientPersistencePort.getAllClient();
    }

    @Override
    public void updateClient(Client client) {
        logger.info("START: updateClient ----> "+client.toString());
        Client oldClient = clientPersistencePort.getClientBySharedKey(client.getSharedKey().toUpperCase().trim());
        if(oldClient != null) {
            client.setEmail(oldClient.getEmail());
            client.setSharedKey(oldClient.getSharedKey());
            client.setUpdateDate(LocalDate.parse(generateFormatDate(LocalDate.now())));
            clientPersistencePort.updateClient(client);
            logger.info("END: updateClient ----> "+client.toString());
        }else {
            throw new NoDataFoundException();
        }

    }

    @Override
    public void deleteClient(String sharedKey) {
        logger.info("START: deleteClient ----> "+sharedKey);
        if(clientValidation.validateSharedKey(sharedKey)) {
            Client oldClient = clientPersistencePort.getClientBySharedKey(sharedKey.toUpperCase().trim());

            if(oldClient.getStateClient().equals(StateEnum.STATE_ACT.getState())) {
                oldClient.setStateClient(StateEnum.ROL_DIS.getState());
                oldClient.setUpdateDate(LocalDate.parse(generateFormatDate(LocalDate.now())));
                clientPersistencePort.updateClient(oldClient);
                logger.info("END: deleteClient");
            }
        }else {
            throw new BadRequestException();
        }
    }

    private boolean validateClient(Client client){
        logger.info("START: validateClient ----> "+client.toString());

        if(Boolean.TRUE.equals(clientValidation.validateEmail(client.getEmail())
            &&clientValidation.validatePhone(client.getPhone())
            &&clientValidation.validateName(client.getNameClient().trim()))) {
            logger.info("END: validateClient ----> "+Boolean.TRUE);
            return Boolean.TRUE;
        }else {
            logger.info("END: validateClient ----> "+Boolean.FALSE);
            return Boolean.FALSE;
        }
    }

    private String generateSharedKey(String email){

        logger.info("START: generateSharedKey ----> "+email);

        String sharedKey=null;
        int limit = email.indexOf(ClientConstant.AT_SIGN);

        if(limit != ClientConstant.ZERO_2_VALUE){
            sharedKey = email.substring(ClientConstant.ZERO_VALUE, limit);
        }
        logger.info("END: generateSharedKey ----> "+sharedKey);
        return sharedKey;
    }

    private String generateFormatDate(LocalDate dateNow){
        logger.info("START: generateFromatDate ----> "+dateNow.toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ClientConstant.FORMAT_DATE);
        return dateNow.format(formatter);
    }

    private boolean validateFilters(String name, String phone, String email, String startDate
            , String endDate){
        if(name.isBlank()&&phone.isBlank()&&email.isBlank()&&startDate.isBlank()&&endDate.isBlank()){
            return Boolean.FALSE;
        }else{
            return Boolean.TRUE;
        }
    }

    private String validateNoNEmpty(String value){
        return (value==null)?"":value;
    }
}
