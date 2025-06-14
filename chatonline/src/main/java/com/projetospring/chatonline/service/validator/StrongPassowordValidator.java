package com.projetospring.chatonline.service.validator;

import java.util.regex.Pattern;

import com.projetospring.chatonline.annotations.StrongPassword;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPassowordValidator implements ConstraintValidator<StrongPassword, String> {

	public static final String UPPERCASE = ".*[A-Z].*";
	public static final Pattern NUMBER = Pattern.compile(".*\\d.*");
	public static final Pattern SPECIAL = Pattern.compile(".*[!@#$%^&*()\\-+=~`{}\\[\\]:;\"'<>,.?/].*");

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		if (password == null) {
			return false;
		}

		context.disableDefaultConstraintViolation();

		if (password.matches(UPPERCASE)) {
			context.buildConstraintViolationWithTemplate("");
		}

		return false;
	}

}
