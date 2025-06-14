package com.projetospring.chatonline.dtos;

import com.projetospring.chatonline.annotations.UniqueEmail;
import com.projetospring.chatonline.annotations.UniqueUsername;

import jakarta.validation.constraints.NotBlank;

public record RegistrationUserDto(@UniqueUsername @NotBlank(message = "The username can not be null") String username,
		@UniqueEmail @NotBlank(message = "The email can not be null") String email,
		@NotBlank(message = "The password can not be null") String password, String confirmationPassword) {

	public boolean isTheSamePassword() {
		return password.equals(confirmationPassword);
	}


}