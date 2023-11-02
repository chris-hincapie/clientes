package com.gml.prueba.clientes.application.handler;

import com.gml.prueba.clientes.application.dto.ClientRequest;
import com.gml.prueba.clientes.application.dto.ClientResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.supercsv.io.ICsvBeanWriter;

import java.io.IOException;
import java.util.List;

public interface IClientHandler {

    void saveClientInMenu(ClientRequest request);
    void updateClientInMenu(ClientResponse update);
    void deleteClientInMenu(String sharedKey);
    ClientResponse getClientBySharedKeyFromMenu(String sharedKey);
    List<ClientResponse> getAllClientsFromMenu();
    List<ClientResponse> getClientWithFiltersFromMenu(String name, String phone, String email, String startDate
            , String endDate);
    ICsvBeanWriter toCsv(List<ClientResponse> request, HttpServletResponse response)  throws IOException;
}
