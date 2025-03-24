package com.ecommerce.notification_service.controller;

import com.ecommerce.notification_service.dto.NotificationDto;
import com.ecommerce.notification_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send")
    public String sendNotification(@RequestBody NotificationDto request) {
        notificationService.sendNotification(request);
        return "Notification sent successfully!";
    }
}
