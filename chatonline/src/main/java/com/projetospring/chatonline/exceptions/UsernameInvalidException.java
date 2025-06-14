package com.projetospring.chatonline.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameInvalidException extends ValidationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameInvalidException(String message) {
		super(message, "USERNAME_IS_NOT_VALID", HttpStatus.CONFLICT);
	}

}
