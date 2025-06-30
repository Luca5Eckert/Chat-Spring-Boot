package com.projetospring.chatonline.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameInvalidException extends ValidationException{

	public UsernameInvalidException(String message) {
		super(message, "USERNAME_IS_NOT_VALID", HttpStatus.CONFLICT);
	}

}
