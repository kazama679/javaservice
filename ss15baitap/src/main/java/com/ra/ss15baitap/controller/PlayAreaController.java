package com.ra.ss15baitap.controller;

import com.ra.ss15baitap.model.dto.response.APIResponse;
import com.ra.ss15baitap.model.entity.PlayArea;
import com.ra.ss15baitap.service.PlayAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/play-areas")
@RequiredArgsConstructor
public class PlayAreaController {
    private final PlayAreaService playAreaService;

    @GetMapping
    public ResponseEntity<APIResponse<List<PlayArea>>> getAll() {
        List<PlayArea> areas = playAreaService.findAll();
        return ResponseEntity.ok(new APIResponse<>(
                "Lấy danh sách khu vui chơi thành công",
                true,
                areas,
                HttpStatus.OK.value()
        ));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @PostMapping
    public ResponseEntity<APIResponse<PlayArea>> create(@RequestBody PlayArea area) {
        PlayArea created = playAreaService.save(area);
        return new ResponseEntity<>(new APIResponse<>(
                "Thêm khu vui chơi thành công",
                true,
                created,
                HttpStatus.CREATED.value()
        ), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<PlayArea>> update(@PathVariable Long id, @RequestBody PlayArea area) {
        PlayArea updated = playAreaService.update(id, area);
        return ResponseEntity.ok(new APIResponse<>(
                "Cập nhật khu vui chơi thành công",
                true,
                updated,
                HttpStatus.OK.value()
        ));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> delete(@PathVariable Long id) {
        playAreaService.delete(id);
        return ResponseEntity.ok(new APIResponse<>(
                "Xóa khu vui chơi thành công",
                true,
                "Đã xóa ID: " + id,
                HttpStatus.OK.value()
        ));
    }
}

