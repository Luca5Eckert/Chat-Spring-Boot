package com.projetospring.chatonline.exceptions;

import org.springframework.http.HttpStatus;

public class PasswordConfirmationException extends ValidationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordConfirmationException(String message) {
		super(message, "PASSWORD_IS_NOT_VALID", HttpStatus.CONFLICT);
	}

	public PasswordConfirmationException() {
		super("Confirmation password is different from password", "PASSWORD_NOT_VALID", HttpStatus.CONFLICT);
	}

}
