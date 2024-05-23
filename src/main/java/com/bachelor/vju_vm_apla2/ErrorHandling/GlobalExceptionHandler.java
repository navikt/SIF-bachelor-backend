package com.bachelor.vju_vm_apla2.ErrorHandling;

import com.bachelor.vju_vm_apla2.ErrorHandling.CustomClientException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomClientException.class)
    public ResponseEntity<String> handleCustomClientException(CustomClientException e) {
        System.out.println("GlobalExceptionHandler - Error occurred: " + e.getMessage());
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
    }
}
