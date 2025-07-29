package com.ra.ss16baitap.service;

import com.ra.ss16baitap.model.dto.request.AuthDTO;
import com.ra.ss16baitap.model.dto.request.LoginRequest;
import com.ra.ss16baitap.model.dto.request.RegisterRequest;
import com.ra.ss16baitap.model.entity.User;
import com.ra.ss16baitap.repository.UserRepository;
import com.ra.ss16baitap.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public void register(RegisterRequest req) {
        if (userRepo.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }
        User user = User.builder()
                .email(req.getEmail())
                .phone(req.getPhone())
                .password(encoder.encode(req.getPassword()))
                .role("ROLE_USER")
                .build();
        userRepo.save(user);
    }

    public AuthDTO login(LoginRequest req) {
        User user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));
        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Sai mật khẩu");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthDTO(token);
    }
}
