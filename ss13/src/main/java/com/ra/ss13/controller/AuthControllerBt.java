package com.ra.ss13.controller;

import com.ra.ss13.model.dto.request.UserLogin;
import com.ra.ss13.model.dto.request.UserRegister;
import com.ra.ss13.model.dto.response.APIResponse;
import com.ra.ss13.model.dto.response.JWTResponse;
import com.ra.ss13.model.entity.UserBt;
import com.ra.ss13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthControllerBt {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<APIResponse<UserBt>> registerUser(@RequestBody UserRegister userRegister) {
        return new ResponseEntity<>(new APIResponse<>(true, "Register user successfully!", userService.registerUser(userRegister), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<JWTResponse>> login(@RequestBody UserLogin userLogin) {
        return new ResponseEntity<>(new APIResponse<>(true, "Login successfully!", userService.login(userLogin), HttpStatus.OK), HttpStatus.OK);
    }
}

