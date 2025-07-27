package com.ra.ss14.service;

import com.ra.ss14.model.entity.AppUser;
import com.ra.ss14.model.entity.RefreshToken;
import com.ra.ss14.security.RefreshTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh-expiration}")
    private long refreshTokenExpiration;

    public RefreshToken createRefreshToken(AppUser user, HttpServletRequest request) {
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expiryDate(LocalDateTime.now().plusMinutes(refreshTokenExpiration))
                .addressIp(request.getRemoteAddr())
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public boolean isExpired(RefreshToken token) {
        return token.getExpiryDate().isBefore(LocalDateTime.now());
    }

    public void deleteByUser(AppUser user) {
        refreshTokenRepository.deleteAllByUser(user);
    }

    public void deleteToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
