package com.gml.prueba.clientes.application.mapper;

import com.gml.prueba.clientes.application.dto.ClientRequest;
import com.gml.prueba.clientes.domain.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ClientRequestMapper {
    @Mapping(source = "name", target = "nameClient")
    Client toClient(ClientRequest clientRequest);
}
