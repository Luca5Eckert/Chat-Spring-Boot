package com.projetospring.chatonline.service.cases;

import com.projetospring.chatonline.dtos.JwtTolkenDto;
import com.projetospring.chatonline.infrastructure.security.UserDetailsImpl;
import com.projetospring.chatonline.infrastructure.tolkens.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public LoginCase(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Transactional
    public JwtTolkenDto execute(@Valid LoginUserDto userLogin) throws AuthenticationValidationException {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password()));

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return jwtService.generateToken(userDetails);
    }

}
