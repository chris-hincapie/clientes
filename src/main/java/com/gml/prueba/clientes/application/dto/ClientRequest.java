package com.gml.prueba.clientes.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClientRequest {
    private String name;
    private String phone;
    private String email;
    private LocalDate startDate;
    private LocalDate endDate;
}
