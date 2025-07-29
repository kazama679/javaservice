package com.ra.ss16baitap.controller;

import com.ra.ss16baitap.security.JwtUtil;
import com.ra.ss16baitap.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final JwtUtil jwtUtil;

    @PostMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<?> addReview(@RequestParam Long productId,
                                       @RequestParam int rating,
                                       @RequestParam String comment,
                                       HttpServletRequest request) {
        Long userId = jwtUtil.extractUserId(request);
        return ResponseEntity.ok(reviewService.addReview(userId, productId, rating, comment));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getReviews(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewsByProduct(id));
    }
}
