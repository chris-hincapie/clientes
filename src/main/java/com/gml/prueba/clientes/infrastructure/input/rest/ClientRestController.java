package com.gml.prueba.clientes.infrastructure.input.rest;

import com.gml.prueba.clientes.application.dto.ClientRequest;
import com.gml.prueba.clientes.application.dto.ClientResponse;
import com.gml.prueba.clientes.application.handler.IClientHandler;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.ICsvBeanWriter;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/menu/client")
@RequiredArgsConstructor
public class ClientRestController {

    private static final Logger logger = Logger.getLogger(ClientRestController.class.getName());

    private final IClientHandler clientHandler;

    @PostMapping
    public ResponseEntity<Void> saveClientInMenu(@RequestBody ClientRequest request){
        logger.info("START: saveClientInMenu ----> "+request.toString());
        clientHandler.saveClientInMenu(request);
        logger.info("END: saveClientInMenu");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateClientInMenu(@RequestBody ClientResponse update){
        logger.info("START: updateClientInMenu ----> "+update.toString());
        clientHandler.updateClientInMenu(update);
        logger.info("END: updateClientInMenu");
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{sharedkey}")
    public ResponseEntity<Void> deleteClientInMenu(@PathVariable(name="sharedkey") String sharedKey){
        logger.info("START: deleteClientInMenu ----> "+sharedKey);
        clientHandler.deleteClientInMenu(sharedKey);
        logger.info("END: deleteClientInMenu");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get/{sharedkey}")
    public ResponseEntity<ClientResponse> getClientBySharedKeyFromMenu(@PathVariable(name="sharedkey") String sharedKey){
        logger.info("START: getClientBySharedKeyFromMenu ----> "+sharedKey);
        return ResponseEntity.ok(clientHandler.getClientBySharedKeyFromMenu(sharedKey));
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAllClientsFromMenu(){
        logger.info("START: getAllClientsFromMenu");
        return ResponseEntity.ok(clientHandler.getAllClientsFromMenu());
    }

    @GetMapping("/get/filter")
    public ResponseEntity<List<ClientResponse>> getClientWithFiltersFromMenu(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "startdate", required = false) String startDate,
            @RequestParam(name = "enddate", required = false) String endDate
    ){
        logger.info("START: getClientWithFiltersFromMenu");
        return ResponseEntity.ok(clientHandler.getClientWithFiltersFromMenu(name, phone, email, startDate, endDate));
    }

    @GetMapping("/get/export")
    public ResponseEntity<Void> exportResultToCSV(@RequestBody List<ClientResponse> request, HttpServletResponse response) throws IOException {
        logger.info("START: exportResultToCSV ----> ");

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition"
                , "attachment: filename=ClientsExport"+System.currentTimeMillis()+".csv");

        ICsvBeanWriter writer =clientHandler.toCsv(request,response);
        writer.close();

        logger.info("END: exportResultToCSV");
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
