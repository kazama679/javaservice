package com.ra.ss12.service;

import com.ra.ss12.model.dto.request.UserRegister;
import com.ra.ss12.model.entity.User;

import java.util.Optional;

public interface UserService {
    User registerUser(UserRegister userRegister);
    Optional<User> findByUsername(String username);
}