/*
 * Made by Leandro Fernandez - 2021.
 */

/*
 * Made by Leandro Fernandez - 2021.
 */

package com.intercorp.exercise.exception;

import com.intercorp.exercise.exception.KpiClientExceptionNotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = KpiClientExceptionNotFound.class)
    protected ResponseEntity<Object> handleKpiClientExceptionNotFound(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.OK, request);
    }
}
