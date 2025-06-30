package com.projetospring.chatonline.exceptions;

import org.springframework.http.HttpStatus;

public class EmailInvalidException extends ValidationException {

	public EmailInvalidException(String message) {
		super(message, "EMAIL_IS_NOT_VALD", HttpStatus.CONFLICT);
	}

}
