package com.projetospring.chatonline.exceptions;

import org.springframework.http.HttpStatus;

public class AuthenticationValidationException extends ValidationException {

	public AuthenticationValidationException(String message) {
		super(message, "AUTHENTICATION_VALIDATION_ERROR", HttpStatus.BAD_REQUEST);
	}

}
