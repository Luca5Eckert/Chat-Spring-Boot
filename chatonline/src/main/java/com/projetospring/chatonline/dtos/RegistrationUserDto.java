package com.projetospring.chatonline.dtos;

import com.projetospring.chatonline.annotations.UniqueEmail;
import com.projetospring.chatonline.annotations.UniqueUsername;

public record RegistrationUserDto(@UniqueUsername String username, @UniqueEmail String email, String password,
		String confirmationPassword) {

	boolean isTheSamePassword() {
		return password.equals(confirmationPassword);
	}

}
