package com.unibuc.fraudservice.exception;

public class UnknownGatewayException extends RuntimeException {

    public UnknownGatewayException() {
    }

    public UnknownGatewayException(String message) {
        super(message);
    }

    public UnknownGatewayException(String message, Throwable cause) {
        super(message, cause);
    }
}
