package com.projetospring.chatonline.service.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projetospring.chatonline.annotations.UniqueEmail;
import com.projetospring.chatonline.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	private UserRepository repository;

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return repository.findByEmail(email).isEmpty();
	}

}
