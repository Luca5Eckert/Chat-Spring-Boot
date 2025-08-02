package com.projetospring.chatonline.core.exceptions;

import org.springframework.http.HttpStatus;

public class AuthenticationValidationException extends ValidationException {

	public AuthenticationValidationException(String message) {
		super(message, "AUTHENTICATION_VALIDATION_ERROR", HttpStatus.BAD_REQUEST);
	}

}
