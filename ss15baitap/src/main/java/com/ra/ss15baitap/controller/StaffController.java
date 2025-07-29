package com.ra.ss15baitap.controller;

import com.ra.ss15baitap.model.dto.response.APIResponse;
import com.ra.ss15baitap.model.entity.Staff;
import com.ra.ss15baitap.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/staffs")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<APIResponse<Staff>> create(@RequestBody Staff staff) {
        return ResponseEntity.ok(new APIResponse<>("Tạo nhân viên", true, staffService.create(staff), 200));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/role")
    public ResponseEntity<APIResponse<Staff>> assignRole(@PathVariable Long id, @RequestParam Staff.Role role) {
        return ResponseEntity.ok(new APIResponse<>("Gán quyền", true, staffService.assignRole(id, role), 200));
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @GetMapping("/me")
    public ResponseEntity<APIResponse<Staff>> me(Principal principal) {
        return ResponseEntity.ok(new APIResponse<>("Thông tin cá nhân", true, staffService.getMyInfo(principal.getName()), 200));
    }

    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    @PutMapping("/me")
    public ResponseEntity<APIResponse<Staff>> updateMe(@RequestBody Staff update, Principal principal) {
        return ResponseEntity.ok(new APIResponse<>("Cập nhật thành công", true, staffService.updateMyInfo(principal.getName(), update), 200));
    }
}
