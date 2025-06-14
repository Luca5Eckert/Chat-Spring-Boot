package com.projetospring.chatonline.dtos;

import com.projetospring.chatonline.annotations.UniqueEmail;
import com.projetospring.chatonline.annotations.UniqueUsername;

import jakarta.validation.constraints.NotBlank;

public record RegistrationUserDto(@UniqueUsername @NotBlank String username, @UniqueEmail @NotBlank String email,
		@NotBlank String password, String confirmationPassword) {

	boolean isTheSamePassword() {
		return password.equals(confirmationPassword);
	}
	

}
