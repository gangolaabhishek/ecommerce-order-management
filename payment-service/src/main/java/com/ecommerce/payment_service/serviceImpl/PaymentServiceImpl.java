package com.ecommerce.payment_service.serviceImpl;

import com.ecommerce.payment_service.entity.PaymentEntity;
import com.ecommerce.payment_service.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public PaymentEntity processPayment(PaymentEntity payment) {
        return null;
    }

    @Override
    public PaymentEntity getPaymentDetailsByOrderId(Long orderId) {
        return null;
    }
}
