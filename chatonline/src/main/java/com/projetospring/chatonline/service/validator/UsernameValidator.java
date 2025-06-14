package com.projetospring.chatonline.service.validator;

import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import com.projetospring.chatonline.annotations.ValidUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

	private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]+$");

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if (username == null) {
			return false;
		}

		context.disableDefaultConstraintViolation();

		boolean isValid = true;

		if (username.contains(" ")) {
			context.buildConstraintViolationWithTemplate("Username cannot contain spaces").addConstraintViolation();
			isValid = false;
		}

		if (!USERNAME_PATTERN.matcher(username).matches()) {
			context.buildConstraintViolationWithTemplate("Username can only contain letters, numbers and underscores")
					.addConstraintViolation();
			isValid = false;
		}

		return isValid;
	}
}