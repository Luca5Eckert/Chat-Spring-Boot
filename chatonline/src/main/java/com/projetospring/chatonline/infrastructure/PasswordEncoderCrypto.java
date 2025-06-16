package com.projetospring.chatonline.infrastructure;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderCrypto {

	public String encryptPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}

	public boolean checkPassword(String passwordAttempted, String password) {
		return BCrypt.checkpw(passwordAttempted, password);
	}

}
