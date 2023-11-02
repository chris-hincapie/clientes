package com.gml.prueba.clientes.domain.model;

import java.time.LocalDate;

public class Client {
    private Long idClient;
    private String sharedKey;
    private String nameClient;
    private String phone;
    private String email;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate createDate;
    private LocalDate updateDate;
    private String stateClient;

    public Client(Long idClient, String sharedKey, String nameClient, String phone, String email, LocalDate startDate
            , LocalDate endDate, LocalDate createDate, LocalDate updateDate, String stateClient) {
        this.idClient = idClient;
        this.sharedKey = sharedKey;
        this.nameClient = nameClient;
        this.phone = phone;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.stateClient = stateClient;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getSharedKey() {
        return sharedKey;
    }

    public void setSharedKey(String sharedKey) {
        this.sharedKey = sharedKey;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public String getStateClient() {
        return stateClient;
    }

    public void setStateClient(String stateClient) {
        this.stateClient = stateClient;
    }
}
