package com.mjc.school.service.exception;

public class ValidationException extends RuntimeException {

	private static final int ERROR_CODE = 201;

	public ValidationException(String message) {
		super(message);
	}

	public int getErrorCode() {
		return ERROR_CODE;
	}
}