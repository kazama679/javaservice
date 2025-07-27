package com.ra.ss14.controller;

import com.ra.ss14.model.dto.request.LoginRequest;
import com.ra.ss14.model.dto.request.RegisterRequest;
import com.ra.ss14.model.dto.response.APIResponse;
import com.ra.ss14.model.entity.BlacklistToken;
import com.ra.ss14.model.entity.RefreshToken;
import com.ra.ss14.repository.BlacklistTokenRepository;
import com.ra.ss14.repository.UserRepository;
import com.ra.ss14.security.JwtUtil;
import com.ra.ss14.security.RefreshTokenRepository;
import com.ra.ss14.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RefreshTokenRepository refreshTokenRepo;
    @Autowired
    private BlacklistTokenRepository blacklistRepo;
    @Autowired
    private UserRepository userRepo;

    @PostMapping("/register")
    public ResponseEntity<APIResponse<String>> register(@RequestBody RegisterRequest request) {
        try {
            String result = authService.register(request);
            APIResponse<String> response = new APIResponse<>(
                    "Đăng ký thành công",
                    true,
                    result,
                    200
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            APIResponse<String> response = new APIResponse<>(
                    ex.getMessage(),
                    false,
                    null,
                    400
            );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<String>> login(@RequestBody LoginRequest request) {
        try {
            String token = authService.login(request);
            APIResponse<String> response = new APIResponse<>(
                    "Đăng nhập thành công",
                    true,
                    token,
                    200
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            APIResponse<String> response = new APIResponse<>(
                    ex.getMessage(),
                    false,
                    null,
                    403
            );
            return ResponseEntity.status(403).body(response);
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> body, HttpServletRequest request) {
        String refreshToken = body.get("refreshToken");
        Optional<RefreshToken> optional = refreshTokenRepo.findByToken(refreshToken);
        if (optional.isEmpty()) return ResponseEntity.status(403).body("Invalid refresh token");

        RefreshToken storedToken = optional.get();
        if (storedToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepo.delete(storedToken);
            return ResponseEntity.status(403).body("Refresh token expired");
        }

        String newAccessToken = jwtUtil.generateToken(storedToken.getToken());
        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, Principal principal, @RequestBody Map<String, String> body) {
        String accessToken = jwtUtil.extractTokenFromRequest(request);
        if (accessToken != null) {
            blacklistRepo.save(BlacklistToken.builder()
                    .token(accessToken)
                    .createdAt(LocalDateTime.now())
                    .build());
        }
        String refreshToken = body.get("refreshToken");
        if (refreshToken != null) refreshTokenRepo.deleteByToken(refreshToken);

        return ResponseEntity.ok("Logout successful");
    }

}