/*
 * Made by Leandro Fernandez - 2021.
 */

package com.intercorp.exercise.util;

import com.intercorp.exercise.dto.response.DeathDateResponse;
import com.intercorp.exercise.model.Client;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MockUtil {

    public static List<Client> getClients(){
        final Client c1 = new Client();
        c1.setId(1L);
        c1.setName("Nombre 1");
        c1.setLastname("Apellido 1");
        c1.setDateOfBirth(LocalDate.now().plusYears(-6));

        final Client c2 = new Client();
        c2.setId(2L);
        c2.setName("Nombre 2");
        c2.setLastname("Apellido 2");
        c2.setDateOfBirth(LocalDate.now().plusYears(-2));

        final Client c3 = new Client();
        c3.setId(3L);
        c3.setName("Nombre 3");
        c3.setLastname("Apellido 3");
        c3.setDateOfBirth(LocalDate.now().plusYears(-3));

        final Client c4 = new Client();
        c4.setId(4L);
        c4.setName("Nombre 4");
        c4.setLastname("Apellido 4");
        c4.setDateOfBirth(LocalDate.now().plusYears(-1));

        return Arrays.asList(c1,c2,c3,c4);
    }

    public static Client getClient(final int plusYear){
        final Client c = new Client();
        c.setId(4L);
        c.setName("Nombre");
        c.setLastname("Apellido");
        c.setDateOfBirth(LocalDate.now().plusYears(plusYear));
        return c;
    }

    public static DeathDateResponse getDeathDateResponse(final int plusYear){
        final DeathDateResponse c = new DeathDateResponse();
        c.setName("Nombre");
        c.setLastname("Apellido");
        c.setDateOfBirth(LocalDate.now().plusYears(plusYear));
        return c;
    }
}
