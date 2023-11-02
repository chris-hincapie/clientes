package com.gml.prueba.clientes.infrastructure.output.jpa.mapper;

import com.gml.prueba.clientes.domain.model.Client;
import com.gml.prueba.clientes.infrastructure.output.jpa.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ClientEntityMapper {
    ClientEntity toEntity(Client client);
    Client toClient(ClientEntity clientEntity);
    List<Client> toClientList(List<ClientEntity> clientEntityList);
}
