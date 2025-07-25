package com.ra.ss13.service.impl;

import com.ra.ss13.model.entity.UserBt;
import com.ra.ss13.repository.UserBtRepository;
import com.ra.ss13.service.UserBtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserBtServiceImpl implements UserBtService {
    @Autowired
    private UserBtRepository userRepository;

    @Override
    public Optional<UserBt> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserBt save(UserBt user) {
        return userRepository.save(user);
    }
}
