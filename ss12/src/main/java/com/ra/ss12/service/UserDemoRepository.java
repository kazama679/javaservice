package com.ra.ss12.service;

import com.ra.ss12.model.entity.UserDemo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDemoRepository extends JpaRepository<UserDemo, Long> {
    Optional<UserDemo> findByUsername(String username);
    boolean existsByUsername(String username);
}
