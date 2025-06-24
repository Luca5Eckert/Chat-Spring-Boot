package com.projetospring.chatonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.dtos.RegistrationUserDto;
import com.projetospring.chatonline.dtos.UserDto;
import com.projetospring.chatonline.exceptions.EmailInvalidException;
import com.projetospring.chatonline.exceptions.PasswordConfirmationException;
import com.projetospring.chatonline.exceptions.UsernameInvalidException;
import com.projetospring.chatonline.infrastructure.PasswordEncoderCrypto;
import com.projetospring.chatonline.model.TypeUser;
import com.projetospring.chatonline.model.User;
import com.projetospring.chatonline.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class RegisterCase {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoderCrypto encoder;

	@Transactional
	public UserDto execute(RegistrationUserDto userRegister) {
		validateRegistrationData(userRegister);
		User user = registerToUser(userRegister);

		repository.save(user);
		return new UserDto(user);
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

	private User registerToUser(RegistrationUserDto userRegister) {
		String encodedPassword = encoder.encryptPassword(userRegister.password());
		return User.createUser(userRegister.username(), userRegister.email(), encodedPassword, TypeUser.USER);
	}

}
