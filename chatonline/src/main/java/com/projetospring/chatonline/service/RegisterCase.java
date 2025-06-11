package com.projetospring.chatonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.dtos.RegistrationUserDto;
import com.projetospring.chatonline.repository.UserRepository;

@Service
public class RegisterCase {

	@Autowired
	private final UserRepository repository;

	public RegisterCase(UserRepository repository) {
		this.repository = repository;
	}

	public boolean execute(RegistrationUserDto registrationUser) {
		return false;
	}

}
