package com.ra.ss16baitap.controller;

import com.ra.ss16baitap.model.dto.request.AuthDTO;
import com.ra.ss16baitap.model.dto.request.LoginRequest;
import com.ra.ss16baitap.model.dto.request.RegisterRequest;
import com.ra.ss16baitap.model.dto.response.APIResponse;
import com.ra.ss16baitap.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController{
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<APIResponse<?>> register(@Valid @RequestBody RegisterRequest request) {
        try {
            authService.register(request);
            return ResponseEntity.ok(
                    new APIResponse<>( "Đăng ký thành công", true, null, 200)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new APIResponse<>(e.getMessage(), false, null, 400)
            );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<AuthDTO>> login(@Valid @RequestBody LoginRequest request) {
        try {
            AuthDTO response = authService.login(request);
            return ResponseEntity.ok(
                    new APIResponse<>("Đăng nhập thành công", true, response, 200)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new APIResponse<>(e.getMessage(), false, null, 400)
            );
        }
    }
}
