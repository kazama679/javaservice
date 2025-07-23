package com.ra.ss12.repository;

import com.ra.ss12.model.entity.UserBai3;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBai3Repository extends JpaRepository<UserBai3, Long> {
    Optional<UserBai3> findByUsername(String username);
}
