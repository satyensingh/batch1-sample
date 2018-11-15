package com.example.demo.ci.service.exception;

public abstract class ClientServiceException extends RuntimeException {

    public ClientServiceException() {
    }

    public ClientServiceException(String message) {
        super(message);
    }

    public ClientServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
