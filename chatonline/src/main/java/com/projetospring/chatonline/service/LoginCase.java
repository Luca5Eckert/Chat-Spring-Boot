package com.projetospring.chatonline.service;

import org.springframework.stereotype.Service;

import com.projetospring.chatonline.dtos.LoginUserDto;

import jakarta.validation.Valid;

@Service
public class LoginCase {

	public void execute(@Valid LoginUserDto userLogin) {
		
	}


}
