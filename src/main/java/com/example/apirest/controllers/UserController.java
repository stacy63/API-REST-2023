package com.example.apirest.controllers;

import com.example.apirest.dtos.UserDto;
import com.example.apirest.exceptions.ExistingUserException;
import com.example.apirest.exceptions.ValidatorException;
import com.example.apirest.services.interfaces.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/user")
public final class UserController {

    final static Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private IUserService iUserService;

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserDto userDto) throws ValidatorException, ExistingUserException {
        iUserService.createNewUserAccount(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
