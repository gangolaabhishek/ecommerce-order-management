package com.ecommerce.notification_service.service;

import com.ecommerce.notification_service.dto.NotificationDto;

public interface NotificationService {
    void sendNotification(NotificationDto notificationRequestDto);
}
