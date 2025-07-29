package com.ra.ss15baitap.service;

import com.ra.ss15baitap.model.dto.request.LoginRequest;
import com.ra.ss15baitap.model.dto.request.RegisterRequest;
import com.ra.ss15baitap.model.dto.request.TokenResponse;

public interface UserService {
    void register(RegisterRequest request);
    TokenResponse login(LoginRequest request, String ip);
    void logout(String username);
}
