package com.example.apirest.controllers;

import com.example.apirest.configurations.jwt.JwtTokenUtil;
import com.example.apirest.dtos.JwtResponseDto;
import com.example.apirest.dtos.LoginDto;
import com.example.apirest.dtos.UserDto;
import com.example.apirest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/login")
public final class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil tokenUtil;

    @PostMapping
    public ResponseEntity<String> createTokenWhenLoggedIn(@RequestBody LoginDto login){
        String userEmail = login.getUserEmail();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail, login.getPassword()));
        final UserDto userDto = userService.loadUserByUsername(userEmail);
        final String jwtToken = tokenUtil.generateJwtToken(userDto);
        return ResponseEntity.ok(new JwtResponseDto(jwtToken).toString());
    }
}
