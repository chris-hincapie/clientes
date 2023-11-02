package com.gml.prueba.clientes.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "clients_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idclient")
    private Long idClient;
    @Column(name = "sharedkey")
    private String sharedKey;
    @Column(name = "nameclient")
    private String nameClient;
    @Column(length = 10)
    private String phone;
    private String email;
    @Column(name = "startdate")
    private LocalDate startDate;
    @Column(name = "enddate")
    private LocalDate endDate;
    @Column(name = "createdate")
    private LocalDate createDate;
    @Column(name = "updatedate")
    private LocalDate updateDate;
    @Column(name = "stateclient")
    private String stateClient;
}
