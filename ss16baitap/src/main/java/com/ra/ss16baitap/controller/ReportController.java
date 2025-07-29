package com.ra.ss16baitap.controller;

import com.ra.ss16baitap.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/revenue")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getRevenue(@RequestParam String type, @RequestParam String value) {
        return ResponseEntity.ok(reportService.getRevenue(type, value));
    }
}
