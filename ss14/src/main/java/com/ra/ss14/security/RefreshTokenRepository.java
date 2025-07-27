package com.ra.ss14.security;

import com.ra.ss14.model.entity.AppUser;
import com.ra.ss14.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByToken(String token);
    List<RefreshToken> findAllByUser(AppUser user);
    void deleteAllByUser(AppUser user);
    List<RefreshToken> findByUser(AppUser user);
    void deleteByUser(AppUser user);
}