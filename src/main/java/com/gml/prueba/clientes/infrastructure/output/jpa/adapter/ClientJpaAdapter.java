package com.gml.prueba.clientes.infrastructure.output.jpa.adapter;

import com.gml.prueba.clientes.domain.model.Client;
import com.gml.prueba.clientes.domain.spi.IClientPersistencePort;
import com.gml.prueba.clientes.infrastructure.exception.ClientAlreadyExistsException;
import com.gml.prueba.clientes.infrastructure.exception.ClientNotFoundException;
import com.gml.prueba.clientes.infrastructure.exception.NoDataFoundException;
import com.gml.prueba.clientes.infrastructure.output.jpa.entity.ClientEntity;
import com.gml.prueba.clientes.infrastructure.output.jpa.mapper.ClientEntityMapper;
import com.gml.prueba.clientes.infrastructure.output.jpa.repository.IClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class ClientJpaAdapter implements IClientPersistencePort {

    private static final Logger logger = Logger.getLogger(ClientJpaAdapter.class.getName());

    @Autowired
    private EntityManager em;

    private final IClientRepository clientRepository;
    private final ClientEntityMapper clientEntityMapper;

    @Override
    public void saveClient(Client client) {
        logger.info("START: saveClient ----> "+client.toString());

        if(clientRepository.findByIdClient(client.getIdClient()).isPresent()){
            throw new ClientAlreadyExistsException();
        }
        clientRepository.save(clientEntityMapper.toEntity(client));

        logger.info("END: saveClient");
    }

    @Override
    public Client getClientBySharedKey(String sharedKey) {
        logger.info("START: getClientBySharedKey ----> "+sharedKey);
        return clientEntityMapper.toClient(clientRepository.findBySharedKey(sharedKey).orElseThrow(ClientNotFoundException::new));
    }

    @Override
    public List<Client> getClientWithFilters(String name, String phone, String email, String startDate
            , String endDate) {
        logger.info("START: getClientWithFilters");

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ClientEntity> query = builder.createQuery(ClientEntity.class);
        Root<ClientEntity> root = query.from(ClientEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            predicates.add(builder.like(root.get("nameClient"), "%" + name + "%"));
        }

        if (phone != null && !phone.isEmpty()) {
            predicates.add(builder.equal(root.get("phone"), phone));
        }

        if (email != null && !email.isEmpty()) {
            predicates.add(builder.equal(root.get("email"), email));
        }

        if (startDate != null && !startDate.isEmpty()) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("startDate"), LocalDate.parse(startDate)));
        }

        if (endDate != null && !endDate.isEmpty()) {
            predicates.add(builder.lessThanOrEqualTo(root.get("endDate"), LocalDate.parse(endDate)));
        }

        query.where(predicates.toArray(new Predicate[0]));
        List<ClientEntity> clientEntityList = em.createQuery(query).getResultList();

        if (clientEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        logger.info("END: getClientWithFilters");
        return clientEntityMapper.toClientList(clientEntityList);
    }

    @Override
    public List<Client> getAllClient() {
        logger.info("START: getAllClient");
        List<ClientEntity> clientEntityList = clientRepository.findAll();
        if (clientEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        logger.info("END: getAllClient");
        return clientEntityMapper.toClientList(clientEntityList);
    }

    @Override
    public void updateClient(Client client) {
        logger.info("START: updateClient ----> "+client.toString());
        clientRepository.save(clientEntityMapper.toEntity(client));
        logger.info("END: updateClient");
    }
}
