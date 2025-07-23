package com.ra.ss12.controller;

import com.ra.ss12.model.dto.request.UserRegister;
import com.ra.ss12.model.dto.response.APIResponse;
import com.ra.ss12.model.entity.User;
import com.ra.ss12.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<APIResponse<User>> registerUser(@RequestBody UserRegister userRegister){
        return new ResponseEntity<>(new APIResponse<>(true,"Regiser user successfully!",userService.registerUser(userRegister), HttpStatus.CREATED), HttpStatus.CREATED);
    }
}