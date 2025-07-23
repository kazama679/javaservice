package com.ra.ss12.service.impl;

import com.ra.ss12.model.dto.request.RegisterRequest;
import com.ra.ss12.model.entity.UserDemo;
import com.ra.ss12.service.UserDemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserDemoRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {
        if (userRepo.existsByUsername(request.getUsername())) {
            return "Tài khoản đã tồn tại!";
        }

        UserDemo user = new UserDemo();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());

        userRepo.save(user);
        return "Đăng ký thành công!";
    }
}
