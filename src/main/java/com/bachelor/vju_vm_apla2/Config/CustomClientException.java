package com.bachelor.vju_vm_apla2.Config;

public class CustomClientException extends RuntimeException {
    private final int statusCode;

    public CustomClientException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

