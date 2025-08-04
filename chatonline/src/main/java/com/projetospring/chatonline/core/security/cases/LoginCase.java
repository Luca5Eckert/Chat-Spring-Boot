package com.projetospring.chatonline.core.security.cases;

import com.projetospring.chatonline.core.dtos.JwtTokenDto;
import com.projetospring.chatonline.infrastructure.security.UserDetailsImpl;
import com.projetospring.chatonline.infrastructure.tokens.JwtService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.core.security.dtos.LoginUserDto;
import com.projetospring.chatonline.core.exceptions.AuthenticationValidationException;

import jakarta.validation.Valid;

@Service
public class LoginCase {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	public JwtTokenDto execute(@Valid LoginUserDto userLogin) throws AuthenticationValidationException {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password()));

		if(authentication.isAuthenticated()){
			throw new AuthenticationValidationException("Invalid credentials");
		}
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		return jwtService.generateAccessToken(userDetails, userDetails.getUser().getId());
	}

}
