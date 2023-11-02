package com.gml.prueba.clientes.domain.api;

import com.gml.prueba.clientes.domain.model.Client;

import java.util.List;

public interface IClientServicePort {
    void saveClient(Client client);
    Client getClient(String sharedKey);
    List<Client> getClientWithFilters(String name, String phone, String email, String startDate
            , String endDate);
    List<Client> getAllClient();
    void updateClient(Client client);
    void deleteClient(String sharedKey);
}
