package com.gml.prueba.clientes.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    CLIENT_NOT_FOUND("No client with that number was found"),
    CLIENT_ALREADY_EXISTS("There is already a client with that SharedKey"),
    NO_DATA_FOUND("No data found for the requested petition"),
    BAD_REQUEST("bad request");

    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
