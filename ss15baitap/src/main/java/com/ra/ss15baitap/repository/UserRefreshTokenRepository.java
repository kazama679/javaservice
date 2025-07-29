package com.ra.ss15baitap.repository;

import com.ra.ss15baitap.model.entity.User;
import com.ra.ss15baitap.model.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {
    Optional<UserRefreshToken> findByTokenRefresh(String token);
    void deleteByUser(User user);
}
