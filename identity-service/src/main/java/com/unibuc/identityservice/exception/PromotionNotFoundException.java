package com.unibuc.identityservice.exception;

public class PromotionNotFoundException extends RuntimeException {

	public PromotionNotFoundException() {
	}

	public PromotionNotFoundException(String message) {
		super(message);
	}

	public PromotionNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
