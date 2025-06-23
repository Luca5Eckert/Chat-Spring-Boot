package com.projetospring.chatonline.controller;

import com.projetospring.chatonline.dtos.JwtTolkenDto;
import com.projetospring.chatonline.dtos.ResponseDto;
import com.projetospring.chatonline.dtos.UserDto;
import com.projetospring.chatonline.model.User;

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

	@PostMapping("/api/user/register")
	public ResponseEntity<ResponseDto> registerUser(@RequestBody @Valid RegistrationUserDto userRegister) {
		UserDto userCreate = registerCase.execute(userRegister);
		
		return ResponseEntity.accepted().body(new ResponseDto(200, "Registration completed successfully", userCreate));
	}

	@PostMapping("/api/user/login")
	public ResponseEntity<ResponseDto> loginInUser(@RequestBody @Valid LoginUserDto userLogin) {
		JwtTolkenDto jwtToken = loginCase.execute(userLogin);
		
		return ResponseEntity.accepted().body(new ResponseDto(200, "Login successful", jwtToken));
	}

}