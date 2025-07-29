package com.ra.ss16baitap.controller;

import com.ra.ss16baitap.model.dto.request.UserDTO;
import com.ra.ss16baitap.model.entity.Role;
import com.ra.ss16baitap.model.entity.User;
import com.ra.ss16baitap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}/role")
    public ResponseEntity<UserDTO> updateUserRole(@PathVariable Long id, @RequestParam Role role) {
        User updated = userService.updateUserRole(id, role);
        return ResponseEntity.ok(UserDTO.fromUser(updated));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        User currentUser = userService.getCurrentUser();
        return ResponseEntity.ok(UserDTO.fromUser(currentUser));
    }
}
