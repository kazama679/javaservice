package com.ra.ss13.service;

import com.ra.ss13.model.dto.request.UserLogin;
import com.ra.ss13.model.dto.request.UserRegister;
import com.ra.ss13.model.dto.response.JWTResponse;
import com.ra.ss13.model.entity.User;

public interface UserService {
    User registerUser(UserRegister userRegister);
    JWTResponse login(UserLogin userLogin);
}