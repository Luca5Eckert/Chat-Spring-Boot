package com.projetospring.chatonline.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.dtos.RegistrationUserDto;
import com.projetospring.chatonline.infrastructure.PasswordEncoder;
import com.projetospring.chatonline.model.TypeUser;
import com.projetospring.chatonline.model.User;
import com.projetospring.chatonline.repository.UserRepository;

@Service
public class RegisterCase {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder encoder;


	public void execute(RegistrationUserDto userRegister) {
		User user = registerToUser(userRegister);
		repository.save(user);
	}
	
	public void checkPasswordConfirmation(RegistrationUserDto userRegister){
		if(!(userRegister.isTheSamePassword())) {
			throw new MethodArgumentNotValidException(null, null);
		}
  }

	private User registerToUser(RegistrationUserDto userRegister) {
		String encodedPassword = encoder.encryptPassword(userRegister.password());
		return User.createUser(null, userRegister.username(), userRegister.email(), encodedPassword, TypeUser.LOGIN,
				LocalDateTime.now());
	}

}
