package com.bachelor.vju_vm_apla2.Config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomClientException.class)
    public ResponseEntity<String> handleCustomClientException(CustomClientException e) {
        // Logger for å gi en detaljert beskrivelse av feilen
        System.out.println("GlobalExceptionHandler - Error occurred: " + e.getMessage());
        // Returner responsentiteten med den spesifikke statuskoden fra CustomClientException
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
    }
}
