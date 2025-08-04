package com.projetospring.chatonline.core.security.cases;

import com.projetospring.chatonline.modules.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.core.security.dtos.RegistrationUserDto;
import com.projetospring.chatonline.modules.user.domain.exceptions.EmailInvalidException;
import com.projetospring.chatonline.modules.user.domain.exceptions.PasswordConfirmationException;
import com.projetospring.chatonline.modules.user.domain.exceptions.UsernameInvalidException;
import com.projetospring.chatonline.infrastructure.PasswordEncoderCrypto;
import com.projetospring.chatonline.modules.user.domain.enums.TypeUser;
import com.projetospring.chatonline.modules.user.aplication.repository.UserRepository;

@Service
public class RegisterCase {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoderCrypto encoder;

	public UserEntity execute(RegistrationUserDto userRegister) {
		validateRegistrationData(userRegister);
		UserEntity user = registerToUser(userRegister);

		return repository.save(user);
	}

	private void validateRegistrationData(RegistrationUserDto userRegister) {
		checkPasswordConfirmation(userRegister);

		checkIfEmailIsUnique(userRegister);

		checkIfUsernameIsUnique(userRegister);

	}

	private void checkPasswordConfirmation(RegistrationUserDto userRegister) {
		if (!(userRegister.isTheSamePassword())) {
			throw new PasswordConfirmationException("Password and confirmation do not match");
		}
	}

	private void checkIfEmailIsUnique(RegistrationUserDto userRegister) {
		if (repository.findByEmail(userRegister.email()).isPresent()) {
			throw new EmailInvalidException("Email is already in use");
		}
	}

	private void checkIfUsernameIsUnique(RegistrationUserDto userRegister) {
		if (repository.findByUsername(userRegister.username()).isPresent()) {
			throw new UsernameInvalidException("Username is already in use");
		}
	}

	private UserEntity registerToUser(RegistrationUserDto userRegister) {
		String encodedPassword = encoder.encryptPassword(userRegister.password());
		return UserEntity.createUser( userRegister.username(), userRegister.email(), encodedPassword, TypeUser.USER);
	}

}
