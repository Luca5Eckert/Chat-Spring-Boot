package com.projetospring.chatonline.service;

import com.projetospring.chatonline.dtos.JwtTolkenDto;
import com.projetospring.chatonline.infrastructure.security.UserDetailsImpl;
import com.projetospring.chatonline.infrastructure.tolkens.JwtService;
import com.projetospring.chatonline.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.dtos.LoginUserDto;
import com.projetospring.chatonline.exceptions.AuthenticationValidationException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class LoginCase {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@Transactional
	public JwtTolkenDto execute(@Valid LoginUserDto userLogin) throws AuthenticationValidationException {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password()));

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		return jwtService.generateToken(userDetails);
	}

}
