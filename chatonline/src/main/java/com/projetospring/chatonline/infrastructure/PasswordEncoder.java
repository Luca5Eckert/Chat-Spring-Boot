package com.projetospring.chatonline.infrastructure;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordEncoder {

	public String encryptPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}

	public boolean checkPassword(String passwordAttempted, String password) {
		return BCrypt.checkpw(passwordAttempted, password);
	}

}
