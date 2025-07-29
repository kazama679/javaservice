package com.ra.ss15baitap.service.impl;

import com.ra.ss15baitap.model.dto.request.TokenResponse;
import com.ra.ss15baitap.model.entity.UserRefreshToken;
import com.ra.ss15baitap.repository.UserRefreshTokenRepository;
import com.ra.ss15baitap.security.JwtUtil;
import com.ra.ss15baitap.service.UserRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRefreshTokenServiceImpl implements UserRefreshTokenService {
    private final UserRefreshTokenRepository tokenRepo;
    private final JwtUtil jwtUtil;

    @Override
    public TokenResponse refreshToken(String token, String ip) {
        UserRefreshToken userToken = tokenRepo.findByTokenRefresh(token)
                .orElseThrow(() -> new RuntimeException("Refresh token không hợp lệ"));

        if (!userToken.getIpAddress().equals(ip))
            throw new RuntimeException("Địa chỉ IP không hợp lệ");

        String newAccessToken = jwtUtil.generateToken(userToken.getUser().getUsername(),
                userToken.getUser().getRoles());
        return new TokenResponse(newAccessToken, token);
    }
}
