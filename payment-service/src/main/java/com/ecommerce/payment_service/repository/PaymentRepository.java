package com.ecommerce.payment_service.repository;

import com.ecommerce.payment_service.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {

    PaymentEntity findByOrderId(Long orderId);
//    Optional<PaymentEntity> byId(Long id);

}
