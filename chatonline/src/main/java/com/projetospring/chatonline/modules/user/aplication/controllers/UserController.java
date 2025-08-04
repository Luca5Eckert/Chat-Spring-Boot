package com.projetospring.chatonline.modules.user.aplication.controllers;

import com.projetospring.chatonline.core.dtos.JwtTolkenDto;
import com.projetospring.chatonline.core.dtos.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetospring.chatonline.core.security.dtos.LoginUserDto;

import com.projetospring.chatonline.core.security.dtos.RegistrationUserDto;
import com.projetospring.chatonline.core.security.cases.LoginCase;
import com.projetospring.chatonline.core.security.cases.RegisterCase;

import jakarta.validation.Valid;

@RestController
public class UserController {


}