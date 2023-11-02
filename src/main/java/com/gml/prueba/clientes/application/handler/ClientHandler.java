package com.gml.prueba.clientes.application.handler;

import com.gml.prueba.clientes.application.dto.ClientRequest;
import com.gml.prueba.clientes.application.dto.ClientResponse;
import com.gml.prueba.clientes.application.mapper.ClientRequestMapper;
import com.gml.prueba.clientes.application.mapper.ClientResponseMapper;
import com.gml.prueba.clientes.domain.api.IClientServicePort;
import com.gml.prueba.clientes.domain.model.Client;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientHandler implements IClientHandler{

    private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());

    private final IClientServicePort clientServicePort;
    private final ClientRequestMapper clientRequestMapper;
    private final ClientResponseMapper clientResponseMapper;

    @Override
    public void saveClientInMenu(ClientRequest request) {
        logger.info("START: saveClientInMenu ----> "+request.toString());
        Client client = clientRequestMapper.toClient(request);
        clientServicePort.saveClient(client);
        logger.info("END: saveClientInMenu ----> "+client.toString());
    }

    @Override
    public void updateClientInMenu(ClientResponse update) {
        logger.info("START: updateClientInMenu ----> "+update.toString());
        Client clientOld = clientServicePort.getClient(update.getSharedKey());
        Client clientNew = new Client(clientOld.getIdClient(),clientOld.getSharedKey(), update.getBusinessId()
                , update.getPhone(), clientOld.getEmail(), clientOld.getStartDate(), clientOld.getEndDate()
                , clientOld.getCreateDate(), clientOld.getUpdateDate(), clientOld.getStateClient());
        clientServicePort.updateClient(clientNew);
        logger.info("END: updateClientInMenu ----> "+clientNew.toString());
    }

    @Override
    public void deleteClientInMenu(String sharedKey) {
        logger.info("START: deleteClientInMenu ----> "+sharedKey);
        clientServicePort.deleteClient(sharedKey);
        logger.info("END: deleteClientInMenu");
    }

    @Override
    public ClientResponse getClientBySharedKeyFromMenu(String sharedKey) {
        logger.info("START: getClientBySharedKeyFromMenu ----> "+sharedKey);
        Client clientGet = clientServicePort.getClient(sharedKey);
        logger.info("END: getClientBySharedKeyFromMenu ----> "+clientGet);
        return clientResponseMapper.toResponse(clientGet);
    }

    @Override
    public List<ClientResponse> getAllClientsFromMenu() {
        logger.info("START: getAllClientsFromMenu");
        return clientResponseMapper.toResponselist(clientServicePort.getAllClient());
    }

    @Override
    public List<ClientResponse> getClientWithFiltersFromMenu(String name, String phone, String email
            , String startDate, String endDate) {
        logger.info("START: getClientWithFiltersFromMenu");
        return clientResponseMapper.toResponselist(clientServicePort.getClientWithFilters(name, phone, email
                , startDate, endDate));
    }

    @Override
    public ICsvBeanWriter toCsv(List<ClientResponse> request, HttpServletResponse response) throws IOException {
        logger.info("START: toCsv");
        ICsvBeanWriter wr = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] headers ={"SHAREDKEY","BUSINESSID","PHONE","EMAIL", "DATAADDED"};
        String[] props ={"sharedKey","businessId","phone","email", "dataAdded"};

        wr.writeHeader(headers);

        for (ClientResponse client: request) {
            wr.write(client, props);
        }

        logger.info("END: toCsv");
        return wr;
    }

}
