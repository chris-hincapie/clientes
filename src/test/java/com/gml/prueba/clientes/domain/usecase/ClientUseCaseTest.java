package com.gml.prueba.clientes.domain.usecase;

import com.gml.prueba.clientes.domain.model.Client;
import com.gml.prueba.clientes.domain.spi.IClientPersistencePort;
import com.gml.prueba.clientes.domain.validation.ClientValidation;
import com.gml.prueba.clientes.infrastructure.configuration.constant.ClientConstant;
import com.gml.prueba.clientes.infrastructure.exception.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientUseCaseTest {

    @InjectMocks
    private ClientUseCase clientUseCase;

    @Mock
    private Logger logger;

    @Mock
    private ClientValidation clientValidation;

    @Mock
    private IClientPersistencePort clientPersistencePort;

    @BeforeEach
    void setUp() {
    }

    String dateString = "2000-08-02";
    DateTimeFormatter formatte = DateTimeFormatter.ofPattern(ClientConstant.FORMAT_DATE);
    LocalDate testCreatedDateSim = LocalDate.parse(dateString, formatte);
    LocalDate testEndDateSim = LocalDate.parse(dateString, formatte);
    LocalDate testStartDateSim = LocalDate.parse(dateString, formatte);
    LocalDate testUpdatedDateSim = LocalDate.parse(dateString, formatte);

    private Client clientTestUpdate = new Client(3L, "prueba1", "nombre prueba"
            , "3159876958","prueba@prueba.com", testStartDateSim, testEndDateSim, testCreatedDateSim
            , testUpdatedDateSim, "ACTIVE");
    private Client clientTestSave = new Client(2L, "prueba2", "nombre prueba2"
            , "3159876958","prueba@prueba.com", testStartDateSim, testEndDateSim, testCreatedDateSim
            , testUpdatedDateSim, "ACTIVE");
    private Client clientExpected = new Client(3L, "prueba3", "nombre prueba3"
            , "3159876958","prueba@prueba.com", testStartDateSim, testEndDateSim, testCreatedDateSim
            , testUpdatedDateSim, "ACTIVE");
    private Client clientTestSaveBad = new Client(4L, "prueba3", "nombre prueba3"
            , "3159876958","pruebaprueba.com", testStartDateSim, testEndDateSim, testCreatedDateSim
            , testUpdatedDateSim, "ACTIVE");

    @Test
    void saveClientSuccess() {
        clientUseCase.saveClient(clientTestSave);
    }

    @Test
    void saveClientBadValid() {
        assertThrows(BadRequestException.class,() ->{
            clientUseCase.saveClient(clientTestSaveBad);
        });
    }

    @Test
    void getClientSucess() {
        when(clientPersistencePort.getClientBySharedKey(anyString())).thenReturn(clientExpected);
        Client clientResult = clientUseCase.getClient("prueba3");
        Assertions.assertEquals(clientExpected, clientResult);
    }

    @Test
    void getClientBad() {
        assertThrows(BadRequestException.class,() ->{
            clientUseCase.getClient("pr");
        });
    }

    @Test
    void getClientWithFilters() {
        when(clientPersistencePort.getClientWithFilters(anyString(),anyString(),anyString(),anyString(),anyString()))
                .thenReturn(Arrays.asList(clientExpected));
        when(clientPersistencePort.getAllClient()).thenReturn(Arrays.asList(clientExpected));
        List<Client> listResult = clientUseCase.getClientWithFilters("prueba nombre", "+573216548936"
                , "prueba@prueba.com", "2023-11-02","2023-11-02");
        Assertions.assertEquals(Arrays.asList(clientExpected), listResult);
    }

    @Test
    void getAllClient() {
        when(clientPersistencePort.getAllClient()).thenReturn(Arrays.asList(clientExpected));
        List<Client> clientResult = clientUseCase.getAllClient();
        Assertions.assertEquals(Arrays.asList(clientExpected), clientResult);
    }

    @Test
    void updateClient() {
        when(clientPersistencePort.getClientBySharedKey(anyString())).thenReturn(clientExpected);
        clientUseCase.updateClient(clientTestUpdate);
    }

    @Test
    void deleteClient() {
        when(clientPersistencePort.getClientBySharedKey(anyString())).thenReturn(clientExpected);
        clientUseCase.deleteClient("prueba3");
    }
}