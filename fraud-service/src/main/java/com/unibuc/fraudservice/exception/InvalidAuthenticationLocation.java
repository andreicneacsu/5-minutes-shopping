package com.unibuc.fraudservice.exception;

public class InvalidAuthenticationLocation extends RuntimeException {

	public InvalidAuthenticationLocation() {
	}

	public InvalidAuthenticationLocation(String message) {
		super(message);
	}

	public InvalidAuthenticationLocation(String message, Throwable cause) {
		super(message, cause);
	}
}
