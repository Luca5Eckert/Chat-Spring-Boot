package com.projetospring.chatonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetospring.chatonline.service.LoginCase;
import com.projetospring.chatonline.service.RegisterCase;

@RestController
public class UserController {

	@Autowired
	private LoginCase caseLogin;
	
	@Autowired
	private RegisterCase caseRegister;
	
	
}
