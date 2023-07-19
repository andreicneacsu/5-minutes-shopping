package com.unibuc.storeservice.exception;

public class StoreScheduleNotFoundException extends RuntimeException {

	public StoreScheduleNotFoundException() {
	}

	public StoreScheduleNotFoundException(String message) {
		super(message);
	}

	public StoreScheduleNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
