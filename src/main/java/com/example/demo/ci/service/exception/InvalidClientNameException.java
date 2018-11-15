package com.example.demo.ci.service.exception;

public class InvalidClientNameException extends ClientServiceException {

    public InvalidClientNameException() {
        super("An invalid client name was specified");
    }
}
