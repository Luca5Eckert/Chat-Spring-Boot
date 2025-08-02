package com.projetospring.chatonline.core.security.dtos;

import com.projetospring.chatonline.modules.user.domain.annotations.StrongPassword;
import com.projetospring.chatonline.modules.user.domain.annotations.ValidEmail;
import com.projetospring.chatonline.modules.user.domain.annotations.ValidUsername;

import jakarta.validation.constraints.NotBlank;

public record RegistrationUserDto(@ValidUsername @NotBlank(message = "The username can not be null") String username,
		@ValidEmail @NotBlank(message = "The email can not be null") String email,
		@StrongPassword @NotBlank(message = "The password can not be null") String password,
		String confirmationPassword) {

	public boolean isTheSamePassword() {
		return password.equals(confirmationPassword);
	}

}