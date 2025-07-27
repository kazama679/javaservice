package com.ra.ss14.service;

import com.ra.ss14.model.entity.JwtBlacklist;
import com.ra.ss14.repository.JwtBlacklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class JwtBlackListService {
    private final JwtBlacklistRepository jwtBlacklistRepository;

    public void blacklistToken(String token, LocalDateTime expiryTime) {
        JwtBlacklist blacklist = JwtBlacklist.builder()
                .token(token)
                .expiryDate(expiryTime)
                .build();
        jwtBlacklistRepository.save(blacklist);
    }

    public boolean isTokenBlacklisted(String token) {
        return jwtBlacklistRepository.existsByToken(token);
    }

    public void cleanExpiredTokens() {
        jwtBlacklistRepository.deleteByExpiryDateBefore(LocalDateTime.now());
    }
}
