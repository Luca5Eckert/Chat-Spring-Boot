package com.projetospring.chatonline.core.exceptions;

import org.springframework.http.HttpStatus;

public class PermissionUserInvalidException extends ValidationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PermissionUserInvalidException(String message) {
		super(message, "USER_DONT_HAVE_PERMISSION", HttpStatus.BAD_REQUEST);
	}

}
