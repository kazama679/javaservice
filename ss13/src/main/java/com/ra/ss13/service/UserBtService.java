package com.ra.ss13.service;

import com.ra.ss13.model.entity.UserBt;

import java.util.Optional;

public interface UserBtService {
    Optional<UserBt> findByUsername(String username);
    UserBt save(UserBt user);
}
