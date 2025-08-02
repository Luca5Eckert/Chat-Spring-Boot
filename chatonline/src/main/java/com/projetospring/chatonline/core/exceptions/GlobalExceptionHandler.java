package com.projetospring.chatonline.core.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.projetospring.chatonline.dtos.ResponseDto;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return ResponseEntity.badRequest().body(new ResponseDto(400, errors.toString(), null));
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ResponseDto> handleValidationException(ValidationException ex) {
		return ResponseEntity.status(ex.getStatus().value()).body(new ResponseDto(401, ex.getMessage(), null));
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ResponseDto> handleBadCredentials(BadCredentialsException ex) {
		return ResponseEntity.badRequest().body(new ResponseDto(401, "Invalid Credentials", null));
	}
}