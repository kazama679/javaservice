package com.ra.ss15baitap.controller;

import com.ra.ss15baitap.service.impl.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/revenue")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getRevenue(@RequestParam String type, @RequestParam String value) {
        return ResponseEntity.ok(Map.of("revenue", reportService.getRevenue(type, value)));
    }

    @GetMapping("/attendance")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAttendance(@RequestParam String type, @RequestParam String value) {
        return ResponseEntity.ok(Map.of("attendance", reportService.getAttendance(type, value)));
    }

    @GetMapping("/combo-usage")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getComboUsage(@RequestParam String type, @RequestParam String value) {
        return ResponseEntity.ok(Map.of("comboUsage", reportService.getComboUsage(type, value)));
    }
}
