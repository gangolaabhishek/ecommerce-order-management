package com.ecommerce.notification_service.repository;

import com.ecommerce.notification_service.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification,Long> {

}
