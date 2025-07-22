package com.ra.ss10.service.impl;

import com.ra.ss10.model.entity.Notification;
import com.ra.ss10.repo.NotificationRepository;
import com.ra.ss10.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    public void sendNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotificationsByAccountId(UUID accountId) {
        return notificationRepository.findByAccount_Id(accountId);
    }
}
