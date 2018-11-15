package com.example.demo.ci.service.exception;

public class ClientNameAlreadyExistsException extends ClientServiceException {

    public ClientNameAlreadyExistsException() {
        super("There is already a client with the specified name");
    }
}
