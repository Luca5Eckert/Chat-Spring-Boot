package com.projetospring.chatonline.dtos;

import com.projetospring.chatonline.annotations.EmailUnique;
import com.projetospring.chatonline.annotations.UsernameUnique;

public record RegistrationUserDto(@UsernameUnique String username, @EmailUnique String email, String password,
		String confirmationPassword) {

	boolean isTheSamePassword() {
		return password.equals(confirmationPassword);
	}

}
