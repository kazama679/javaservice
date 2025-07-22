package com.ra.ss10.controller;

import com.ra.ss10.model.entity.Notification;
import com.ra.ss10.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable UUID accountId) {
        List<Notification> list = notificationService.getNotificationsByAccountId(accountId);
        return ResponseEntity.ok(list);
    }
}
