package com.projetospring.chatonline.service.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.projetospring.chatonline.annotations.UsernameUnique;
import com.projetospring.chatonline.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UsernameUnique, String> {
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		return repository.findByUsername(username).isEmpty();
	}

}
