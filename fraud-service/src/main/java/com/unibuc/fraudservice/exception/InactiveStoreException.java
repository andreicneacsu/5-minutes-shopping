package com.unibuc.fraudservice.exception;

public class InactiveStoreException extends RuntimeException {

	public InactiveStoreException() {
	}

	public InactiveStoreException(String message) {
		super(message);
	}

	public InactiveStoreException(String message, Throwable cause) {
		super(message, cause);
	}
}
