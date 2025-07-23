package com.ra.ss12.controller;

import com.ra.ss12.model.dto.request.LoginRequest;
import com.ra.ss12.model.dto.request.RegisterRequest;
import com.ra.ss12.model.dto.request.UserRegister;
import com.ra.ss12.model.entity.User;
import com.ra.ss12.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController10 {
    @Autowired
    private UserService userService;
    @Autowired private PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        UserRegister userRegister = new UserRegister();
        userRegister.setUsername(request.getUsername());
        userRegister.setPassword(request.getPassword());
        userRegister.setEmail(request.getEmail());
        userService.registerUser(userRegister);
        return ResponseEntity.ok("Đăng ký thành công");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest,
                                        HttpSession session) {
        Optional<User> user = userService.findByUsername(loginRequest.getUsername());
        if (user.isPresent() && encoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            session.setAttribute("username", user.get().getUsername());
            return ResponseEntity.ok("Đăng nhập thành công");
        }
        return ResponseEntity.badRequest().body("Sai thông tin đăng nhập");
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Đã đăng xuất");
    }
}
