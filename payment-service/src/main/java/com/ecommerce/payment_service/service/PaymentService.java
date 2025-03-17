package com.ecommerce.payment_service.service;

import com.ecommerce.payment_service.entity.PaymentEntity;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    PaymentEntity processPayment(PaymentEntity payment);
    PaymentEntity getPaymentDetailsByOrderId(Long orderId);
}
