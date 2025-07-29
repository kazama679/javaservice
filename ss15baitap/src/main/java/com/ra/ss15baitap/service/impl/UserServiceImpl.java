package com.ra.ss15baitap.service.impl;

import com.ra.ss15baitap.model.dto.request.LoginRequest;
import com.ra.ss15baitap.model.dto.request.RegisterRequest;
import com.ra.ss15baitap.model.dto.request.TokenResponse;
import com.ra.ss15baitap.model.entity.User;
import com.ra.ss15baitap.model.entity.UserRefreshToken;
import com.ra.ss15baitap.repository.UserRefreshTokenRepository;
import com.ra.ss15baitap.repository.UserRepository;
import com.ra.ss15baitap.security.JwtUtil;
import com.ra.ss15baitap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final UserRefreshTokenRepository tokenRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public void register(RegisterRequest request) {
        if (userRepo.existsByUsername(request.getUsername()) || userRepo.existsByEmail(request.getEmail()))
            throw new RuntimeException("Username hoặc email đã tồn tại");

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .phone(request.getPhone())
                .roles(Set.of("USER"))
                .isLogin(false)
                .isStatus(true)
                .build();
        userRepo.save(user);
    }

    @Override
    public TokenResponse login(LoginRequest request, String ip) {
        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new RuntimeException("Mật khẩu sai");
        user.setIsLogin(true);
        userRepo.save(user);
        String accessToken = jwtUtil.generateToken(user.getUsername(), user.getRoles());
        String refreshToken = UUID.randomUUID().toString();
        tokenRepo.deleteByUser(user);
        tokenRepo.save(UserRefreshToken.builder()
                .user(user)
                .tokenRefresh(refreshToken)
                .ipAddress(ip)
                .build());
        return new TokenResponse(accessToken, refreshToken);
    }

    @Override
    public void logout(String username) {
        User user = userRepo.findByUsername(username).orElseThrow();
        user.setIsLogin(false);
        userRepo.save(user);
        tokenRepo.deleteByUser(user);
    }
}
