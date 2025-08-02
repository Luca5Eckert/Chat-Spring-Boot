package com.projetospring.chatonline.core.exceptions;

import org.springframework.http.HttpStatus;

public class PermissionUserInvalidException extends ValidationException {


	public PermissionUserInvalidException(String message) {
		super(message, "USER_DONT_HAVE_PERMISSION", HttpStatus.BAD_REQUEST);
	}

}
