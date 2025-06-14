package com.projetospring.chatonline.service.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projetospring.chatonline.annotations.UniqueUsername;
import com.projetospring.chatonline.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	@Autowired
	private UserRepository repository;

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		return repository.findByUsername(username).isEmpty();
	}

}
