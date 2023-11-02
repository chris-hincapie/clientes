package com.gml.prueba.clientes.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClientResponse {
    private String sharedKey;
    private String businessId;
    private String phone;
    private String email;
    private LocalDate dataAdded;
}
