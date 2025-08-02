package com.projetospring.chatonline.core.exceptions;

import org.springframework.http.HttpStatus;

public class AuthenticationValidationException extends ValidationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthenticationValidationException(String message) {
		super(message, "AUTHENTICATION_VALIDATION_ERROR", HttpStatus.BAD_REQUEST);
	}

}
