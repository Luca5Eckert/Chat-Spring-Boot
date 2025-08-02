package com.projetospring.chatonline.modules.user.domain.exceptions;

import com.projetospring.chatonline.core.exceptions.ValidationException;
import org.springframework.http.HttpStatus;

public class PasswordConfirmationException extends ValidationException {

	public PasswordConfirmationException(String message) {
		super(message, "PASSWORD_IS_NOT_VALID", HttpStatus.CONFLICT);
	}

	public PasswordConfirmationException() {
		super("Confirmation password is different from password", "PASSWORD_NOT_VALID", HttpStatus.CONFLICT);
	}

}
