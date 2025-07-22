package com.ra.ss10.service;

import com.ra.ss10.model.entity.Notification;

import java.util.List;
import java.util.UUID;

public interface NotificationService {
    void sendNotification(Notification notification);
    List<Notification> getNotificationsByAccountId(UUID accountId);
}