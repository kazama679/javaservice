package com.ra.ss13.repository;

import com.ra.ss13.model.entity.UserBt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserBtRepository extends JpaRepository<UserBt, Long> {
    Optional<UserBt> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
