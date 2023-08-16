package com.unibuc.identityservice.exception;

public class InvalidPromotionTypeException extends RuntimeException {

	public InvalidPromotionTypeException() {
	}

	public InvalidPromotionTypeException(String message) {
		super(message);
	}

	public InvalidPromotionTypeException(String message, Throwable cause) {
		super(message, cause);
	}
}
