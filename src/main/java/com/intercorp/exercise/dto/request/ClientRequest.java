/*
 * Made by Leandro Fernandez - 2021.
 */

package com.intercorp.exercise.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ClientRequest {
    @NotBlank(message = "Name is mandatory")
    @ApiModelProperty(name = "name", example = "Leandro", notes = "Nombre del cliente")
    private String name;

    @NotBlank(message = "Lastname is mandatory")
    @ApiModelProperty(name = "lastname", example = "Fernandez", notes = "Apellido del cliente")
    private String lastname;

    @NotNull(message = "Date of Birth is mandatory")
    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(name = "dateOfBirth", example = "1988-07-01", notes = "Fecha de nacimiento")
    private LocalDate dateOfBirth;
}
