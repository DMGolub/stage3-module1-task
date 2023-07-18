package com.mjc.school.service.exception;

public final class EntityNotFoundException extends RuntimeException {

	private static final int ERROR_CODE = 101;

	public EntityNotFoundException(String message) {
		super(message);
	}

	public int getErrorCode() {
		return ERROR_CODE;
	}
}