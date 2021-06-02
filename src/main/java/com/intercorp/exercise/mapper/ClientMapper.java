/*
 * Made by Leandro Fernandez - 2021.
 */

package com.intercorp.exercise.mapper;

import com.intercorp.exercise.dto.request.ClientRequest;
import com.intercorp.exercise.dto.response.DeathDateResponse;
import com.intercorp.exercise.dto.response.ClientResponse;
import com.intercorp.exercise.model.Client;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client mapToClientEntity(final ClientRequest clientRequest){
        return new ModelMapper().map(clientRequest,Client.class);
    }

    public ClientResponse mapToClientDTO(final Client client){
        return new ModelMapper().map(client, ClientResponse.class);
    }

    public DeathDateResponse mapToDeathDateDTO(final Client client){
        return new ModelMapper().map(client, DeathDateResponse.class);
    }
}
