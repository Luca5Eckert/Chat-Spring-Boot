package com.projetospring.chatonline.modules.user.domain.exceptions;

import com.projetospring.chatonline.core.exceptions.ValidationException;
import org.springframework.http.HttpStatus;

public class EmailInvalidException extends ValidationException {

	public EmailInvalidException(String message) {
		super(message, "EMAIL_IS_NOT_VALD", HttpStatus.CONFLICT);
	}

}
