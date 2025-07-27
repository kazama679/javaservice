package com.ra.ss14.service;

import com.ra.ss14.model.dto.request.*;
import com.ra.ss14.model.entity.AppUser;
import com.ra.ss14.repository.UserRepository;
import com.ra.ss14.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private AuthenticationManager authManager;
    @Autowired
    private EmailService emailService;
    @Autowired
    private TokenService tokenService;

    public String register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("Username đã tồn tại");
        if (userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("Email đã tồn tại");

        AppUser user = AppUser.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_USER")
                .build();

        userRepository.save(user);
        return "Đăng ký thành công";
    }

    public String login(LoginRequest request) {
        AppUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Sai mật khẩu");
        }
        return jwtUtil.generateToken(user.getUsername());
    }

    public ResponseEntity<String> loginWithOtp(LoginRequest request) {
        AppUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Sai mật khẩu");
        }

        String otp = generateOtp();
        user.setOtp(otp);
        user.setVerifiedOtp(false);
        userRepository.save(user);

        emailService.sendOtp(user.getEmail(), otp);

        return ResponseEntity.ok("OTP đã được gửi đến email của bạn");
    }

    public ResponseEntity<TokenResponse> verifyOtp(OtpVerifyRequest request) {
        AppUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Không tồn tại tài khoản"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new RuntimeException("Sai mật khẩu");
        if (!request.getOtp().equals(user.getOtp()))
            throw new RuntimeException("Mã OTP không đúng");
        user.setVerifiedOtp(true);
        user.setOtp(null);
        userRepository.save(user);
        tokenService.cleanupOldSessions(user);
        String accessToken = tokenService.generateAccessToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);
        tokenService.saveRefreshToken(user, refreshToken);
        return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken));
    }

    public ResponseEntity<String> logoutAllDevice(LogoutAllRequest request) {
        AppUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user"));

        tokenService.deleteAllByUser(user);
        return ResponseEntity.ok("Đã đăng xuất toàn bộ thiết bị");
    }

    private String generateOtp() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }
}
