package com.gml.prueba.clientes.domain.spi;

import com.gml.prueba.clientes.domain.model.Client;

import java.util.List;

public interface IClientPersistencePort {
    void saveClient(Client client);
    Client getClientBySharedKey(String sharedKey);
    List<Client> getClientWithFilters(String name, String phone, String email, String startDate
            , String endDate);
    List<Client> getAllClient();
    void updateClient(Client client);
}
