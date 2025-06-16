package com.projetospring.chatonline.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.dtos.LoginUserDto;
import com.projetospring.chatonline.exceptions.AuthenticationValidationException;

import jakarta.validation.Valid;

@Service
public class LoginCase {

	@Autowired
	private AuthenticationManager authenticationManager;

	public void execute(@Valid LoginUserDto userLogin) {
		UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userLogin.username(),
				userLogin.password());

		try {
			Authentication authenticated = authenticationManager.authenticate(userToken);

			SecurityContextHolder.getContext().setAuthentication(authenticated);
		} catch (Exception ae) {
			throw new AuthenticationValidationException("Error in the authentication");
		}
	}

}
