package com.projetospring.chatonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetospring.chatonline.dtos.LoginUserDto;
import com.projetospring.chatonline.dtos.RegistrationUserDto;
import com.projetospring.chatonline.service.LoginCase;
import com.projetospring.chatonline.service.RegisterCase;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private LoginCase loginCase;

	@Autowired
	private RegisterCase registerCase;

	@PostMapping("/api/register")
	public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationUserDto userRegister) {
		registerCase.execute(userRegister);
		return ResponseEntity.accepted().body("Registration completed successfully");
	}

	@PostMapping("/api/login")
	public ResponseEntity<?> loginInUser(@RequestBody @Valid LoginUserDto userLogin) {
		loginCase.execute(userLogin);
		return ResponseEntity.accepted().body("Login completed successfully");
	}
}
