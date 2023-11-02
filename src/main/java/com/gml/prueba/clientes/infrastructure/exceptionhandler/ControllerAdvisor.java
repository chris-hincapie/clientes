package com.gml.prueba.clientes.infrastructure.exceptionhandler;

import com.gml.prueba.clientes.infrastructure.exception.BadRequestException;
import com.gml.prueba.clientes.infrastructure.exception.ClientAlreadyExistsException;
import com.gml.prueba.clientes.infrastructure.exception.ClientNotFoundException;
import com.gml.prueba.clientes.infrastructure.exception.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUsuarioAlreadyExistsException(
            ClientAlreadyExistsException usuarioAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.CLIENT_ALREADY_EXISTS.getMessage()));
    }
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRolNotFoundException(
            ClientNotFoundException rolNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.CLIENT_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException noDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, String>> handleBadRequestException(
            BadRequestException badRequestException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.BAD_REQUEST.getMessage()));
    }
}
