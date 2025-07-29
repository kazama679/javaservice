package com.ra.ss15baitap.service;

import com.ra.ss15baitap.model.dto.request.TokenResponse;

public interface UserRefreshTokenService {
    TokenResponse refreshToken(String token, String ip);
}

