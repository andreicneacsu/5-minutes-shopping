package com.unibuc.identityservice.exception;

public class ShopperNotFoundException extends RuntimeException {

	public ShopperNotFoundException() {
	}

	public ShopperNotFoundException(String message) {
		super(message);
	}

	public ShopperNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
