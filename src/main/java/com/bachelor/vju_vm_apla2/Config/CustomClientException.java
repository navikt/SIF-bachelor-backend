package com.bachelor.vju_vm_apla2.Config;

public class CustomClientException extends RuntimeException {
    private final int statusCode;
    private final String message;
    private final String origin;

    public CustomClientException(int statusCode, String message, String origin) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
        this.origin = origin;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public String getOrigin() {
        return origin;
    }
}


