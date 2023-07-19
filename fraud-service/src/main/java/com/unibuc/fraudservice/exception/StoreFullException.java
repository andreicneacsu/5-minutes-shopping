package com.unibuc.fraudservice.exception;

public class StoreFullException extends RuntimeException {

	public StoreFullException() {
	}

	public StoreFullException(String message) {
		super(message);
	}

	public StoreFullException(String message, Throwable cause) {
		super(message, cause);
	}
}
