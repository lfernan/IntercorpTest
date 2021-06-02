/*
 * Made by Leandro Fernandez - 2021.
 */

package com.intercorp.exercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class KpiClientExceptionNotFound extends RuntimeException {
    public KpiClientExceptionNotFound(String msg) {
        super(msg);
    }
}
