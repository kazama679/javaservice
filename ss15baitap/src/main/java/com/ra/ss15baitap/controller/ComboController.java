package com.ra.ss15baitap.controller;

import com.ra.ss15baitap.model.dto.response.APIResponse;
import com.ra.ss15baitap.model.entity.Combo;
import com.ra.ss15baitap.service.ComboService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/combos")
@RequiredArgsConstructor
public class ComboController {
    private final ComboService comboService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Combo>>> getAll() {
        List<Combo> combos = comboService.findAll();
        return ResponseEntity.ok(new APIResponse<>(
                "Lấy danh sách combo thành công",
                true,
                combos,
                HttpStatus.OK.value()
        ));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @PostMapping
    public ResponseEntity<APIResponse<Combo>> create(@RequestBody Combo combo) {
        Combo created = comboService.save(combo);
        return new ResponseEntity<>(new APIResponse<>(
                "Thêm combo thành công",
                true,
                created,
                HttpStatus.CREATED.value()
        ), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Combo>> update(@PathVariable Long id, @RequestBody Combo combo) {
        Combo updated = comboService.update(id, combo);
        return ResponseEntity.ok(new APIResponse<>(
                "Cập nhật combo thành công",
                true,
                updated,
                HttpStatus.OK.value()
        ));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> delete(@PathVariable Long id) {
        comboService.delete(id);
        return ResponseEntity.ok(new APIResponse<>(
                "Xóa combo thành công",
                true,
                "Đã xóa combo có ID: " + id,
                HttpStatus.OK.value()
        ));
    }
}
