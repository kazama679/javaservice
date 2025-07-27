package com.ra.ss14.repository;

import com.ra.ss14.model.entity.JwtBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface JwtBlacklistRepository extends JpaRepository<JwtBlacklist, Long> {
    boolean existsByToken(String token);
    void deleteByExpiryDateBefore(LocalDateTime time);
}
