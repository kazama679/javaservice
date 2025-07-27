package com.ra.ss14.service;

import com.ra.ss14.model.entity.AppUser;
import com.ra.ss14.model.entity.RefreshToken;
import com.ra.ss14.security.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ra.ss14.security.JwtUtil;

import java.util.List;

@Service
public class TokenService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public String generateAccessToken(AppUser user) {
        return jwtUtil.generateToken(user.getUsername());
    }

    public String generateRefreshToken(AppUser user) {
        return jwtUtil.generateToken(user.getUsername());
    }

    public void saveRefreshToken(AppUser user, String refreshToken) {
        RefreshToken token = new RefreshToken();
        token.setToken(refreshToken);
        token.setUser(user);
        refreshTokenRepository.save(token);
    }

    public void deleteAllByUser(AppUser user) {
        refreshTokenRepository.deleteByUser(user);
    }

    public void cleanupOldSessions(AppUser user) {
        List<RefreshToken> tokens = refreshTokenRepository.findByUser(user);
        refreshTokenRepository.deleteAll(tokens);
    }
}
