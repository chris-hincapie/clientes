package com.gml.prueba.clientes.infrastructure.configuration.enums;

public enum StateEnum {
    STATE_ACT("ACTIVE"),
    ROL_DIS("DISABLE");

    private String state;

    StateEnum(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }
}
