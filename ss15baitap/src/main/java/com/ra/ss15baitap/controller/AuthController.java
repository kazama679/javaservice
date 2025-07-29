package com.ra.ss15baitap.controller;

import com.ra.ss15baitap.model.dto.request.LoginRequest;
import com.ra.ss15baitap.model.dto.request.RefreshTokenRequest;
import com.ra.ss15baitap.model.dto.request.RegisterRequest;
import com.ra.ss15baitap.model.dto.request.TokenResponse;
import com.ra.ss15baitap.model.dto.response.APIResponse;
import com.ra.ss15baitap.service.UserRefreshTokenService;
import com.ra.ss15baitap.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final UserRefreshTokenService refreshService;

    @PostMapping("/register")
    public ResponseEntity<APIResponse<String>> register(@RequestBody RegisterRequest request) {
        userService.register(request);
        return ResponseEntity.ok(new APIResponse<>(
                "Đăng ký thành công",
                true,
                null,
                HttpStatus.OK.value()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<TokenResponse>> login(@RequestBody LoginRequest request,
                                                            HttpServletRequest http) {
        String ip = http.getRemoteAddr();
        TokenResponse token = userService.login(request, ip);
        return ResponseEntity.ok(new APIResponse<>(
                "Đăng nhập thành công",
                true,
                token,
                HttpStatus.OK.value()
        ));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<APIResponse<TokenResponse>> refresh(@RequestBody RefreshTokenRequest request,
                                                              HttpServletRequest http) {
        String ip = http.getRemoteAddr();
        TokenResponse token = refreshService.refreshToken(request.getRefreshToken(), ip);
        return ResponseEntity.ok(new APIResponse<>(
                "Làm mới access token thành công",
                true,
                token,
                HttpStatus.OK.value()
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<APIResponse<String>> logout(Principal principal) {
        userService.logout(principal.getName());
        return ResponseEntity.ok(new APIResponse<>(
                "Đăng xuất thành công",
                true,
                null,
                HttpStatus.OK.value()
        ));
    }
}
