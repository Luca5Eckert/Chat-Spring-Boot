package com.projetospring.chatonline.exceptions;

import org.springframework.http.HttpStatus;

public class ValidationException extends RuntimeException {

	private final String errorCode;
	private final HttpStatus status;

	public ValidationException(String message, String errorCode, HttpStatus status) {
		super(message);
		this.errorCode = errorCode;
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
