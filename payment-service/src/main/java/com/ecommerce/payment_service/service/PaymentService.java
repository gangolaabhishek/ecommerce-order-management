package com.ecommerce.payment_service.service;

import com.ecommerce.payment_service.dto.PaymentDto;
import com.ecommerce.payment_service.entity.PaymentEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PaymentService {
    PaymentDto processPayment(PaymentDto payment);
    PaymentDto getPaymentDetailsByOrderId(Long orderId);
    Optional<PaymentEntity> getPaymentDetailsById(Long id);
}
