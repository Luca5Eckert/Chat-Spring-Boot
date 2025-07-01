package com.projetospring.chatonline.dtos;

import com.projetospring.chatonline.annotations.StrongPassword;
import com.projetospring.chatonline.annotations.ValidEmail;
import com.projetospring.chatonline.annotations.ValidUsername;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record RegistrationUserDto(@ValidUsername @NotBlank(message = "The username can not be null") @Max(value=20, message="User name cannot be longer than 20 characters") String username,
		@ValidEmail @NotBlank(message = "The email can not be null") String email,
		@StrongPassword @NotBlank(message = "The password can not be null") String password,
		String confirmationPassword) {

	public boolean isTheSamePassword() {
		return password.equals(confirmationPassword);
	}

}