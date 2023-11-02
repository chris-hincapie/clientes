package com.gml.prueba.clientes.application.mapper;

import com.gml.prueba.clientes.application.dto.ClientResponse;
import com.gml.prueba.clientes.domain.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ClientResponseMapper {
    @Mapping(target = "sharedKey", source = "sharedKey")
    @Mapping(target = "businessId", source = "nameClient")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "dataAdded", source = "createDate")
    ClientResponse toResponse(Client client);

    default List<ClientResponse> toResponselist(List<Client> clientList){
        return clientList.stream()
                .map( client -> {
                    ClientResponse clientResponse = new ClientResponse();
                    clientResponse.setEmail(client.getEmail());
                    clientResponse.setPhone(client.getPhone());
                    clientResponse.setSharedKey(client.getSharedKey());
                    clientResponse.setBusinessId(client.getNameClient());
                    clientResponse.setDataAdded(client.getCreateDate());

                    return clientResponse;
                }).toList();
    }
}
