package com.ra.ss15baitap.controller;

import com.ra.ss15baitap.model.dto.request.FeedbackRequest;
import com.ra.ss15baitap.model.entity.Feedback;
import com.ra.ss15baitap.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.ra.ss15baitap.security.UserPrincipal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> createFeedback(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                            @RequestBody FeedbackRequest request) {
        Feedback feedback = feedbackService.createFeedback(userPrincipal.getId(), request);
        return ResponseEntity.ok(feedback);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedback());
    }

    @PutMapping("/{id}/reply")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<?> replyFeedback(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String reply = body.get("reply");
        return ResponseEntity.ok(feedbackService.replyFeedback(id, reply));
    }
}
