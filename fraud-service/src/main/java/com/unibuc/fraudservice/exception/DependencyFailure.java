package com.unibuc.fraudservice.exception;

public class DependencyFailure extends RuntimeException {

    public DependencyFailure() {
        super();
    }

    public DependencyFailure(String message) {
        super(message);
    }

    public DependencyFailure(String message, Throwable cause) {
        super(message, cause);
    }
}
