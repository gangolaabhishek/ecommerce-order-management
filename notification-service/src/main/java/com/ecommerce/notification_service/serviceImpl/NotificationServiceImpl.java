package com.ecommerce.notification_service.serviceImpl;

import com.ecommerce.notification_service.dto.NotificationDto;
import com.ecommerce.notification_service.entity.Notification;
import com.ecommerce.notification_service.repository.NotificationRepo;
import com.ecommerce.notification_service.service.NotificationService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private NotificationRepo notificationRepository;

    @Override
    public void sendNotification(NotificationDto notificationRequestDto) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(notificationRequestDto.getRecipientEmail());
            helper.setSubject(notificationRequestDto.getSubject());
            helper.setText(notificationRequestDto.getMessage(), true);

            mailSender.send(message);

            // Save notification record in database
            Notification notification = new Notification();
            notification.setRecipientEmail(notificationRequestDto.getRecipientEmail());
            notification.setSubject(notificationRequestDto.getSubject());
            notification.setMessage(notificationRequestDto.getMessage());

            notificationRepository.save(notification);

        } catch (MessagingException e) {
            throw new RuntimeException("Error while sending email: " + e.getMessage());
        }
    }
}
