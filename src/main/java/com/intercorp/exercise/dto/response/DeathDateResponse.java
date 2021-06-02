/*
 * Made by Leandro Fernandez - 2021.
 */

/*
 * Made by Leandro Fernandez - 2021.
 */

package com.intercorp.exercise.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intercorp.exercise.dto.request.ClientRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeathDateResponse extends ClientRequest {

    @JsonProperty("probable_date_of_death")
    private LocalDate deathDate;
}
