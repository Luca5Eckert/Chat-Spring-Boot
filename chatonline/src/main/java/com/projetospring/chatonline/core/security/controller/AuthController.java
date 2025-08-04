package com.projetospring.chatonline.core.security.controller;

import com.projetospring.chatonline.core.dtos.JwtTolkenDto;
import com.projetospring.chatonline.core.dtos.ResponseDto;
import com.projetospring.chatonline.core.security.cases.LoginCase;
import com.projetospring.chatonline.core.security.cases.RegisterCase;
import com.projetospring.chatonline.core.security.dtos.LoginUserDto;
import com.projetospring.chatonline.core.security.dtos.RegistrationUserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {


    @Autowired
    private LoginCase loginCase;

    @Autowired
    private RegisterCase registerCase;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationUserDto userRegister) {
        registerCase.execute(userRegister);
        return ResponseEntity.accepted().body("Registration completed successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginInUser(@RequestBody @Valid LoginUserDto userLogin) {
        JwtTolkenDto jwtToken = loginCase.execute(userLogin);
        return ResponseEntity.accepted().body( new ResponseDto(200, "Login successful", jwtToken));
    }

}
