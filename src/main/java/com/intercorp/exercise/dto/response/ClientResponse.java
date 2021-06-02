/*
 * Made by Leandro Fernandez - 2021.
 */

package com.intercorp.exercise.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientResponse {
    private Long id;
    private String name;
    private String lastname;
    private LocalDate dateOfBirth;
}
