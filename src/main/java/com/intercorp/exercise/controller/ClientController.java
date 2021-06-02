/*
 * Made by Leandro Fernandez - 2021.
 */

package com.intercorp.exercise.controller;

import com.intercorp.exercise.dto.request.ClientRequest;
import com.intercorp.exercise.dto.response.ClientResponse;
import com.intercorp.exercise.dto.response.DeathDateResponse;
import com.intercorp.exercise.dto.response.KpiClientsResponse;
import com.intercorp.exercise.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/creacliente")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ClientResponse createClient(@RequestBody @Validated ClientRequest clientRequest){
        return this.clientService.createClient(clientRequest);
    }

    @GetMapping("kpideclientes")
    public @ResponseBody ResponseEntity<KpiClientsResponse> getKpiClient(){
        return new ResponseEntity(this.clientService.getKpiClient(), HttpStatus.OK);
    }

    @GetMapping("listclientes")
    public @ResponseBody ResponseEntity<List<DeathDateResponse>> listclientes(){
        return new ResponseEntity(this.clientService.getClientListWithDD(), HttpStatus.OK);
    }
}
