package com.projetospring.chatonline.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> handlesValidationErrors(MethodArgumentNotValidException manve) {
		List<String> mensagens = manve.getBindingResult().getFieldErrors().stream().map(err -> err.getDefaultMessage())
				.toList();

		return ResponseEntity.badRequest().body(mensagens);
	}
	
	
}
