package com.projetospring.chatonline.exceptions;

public class EmailInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailInvalidException(String message) {
		super(message);
	}

}
