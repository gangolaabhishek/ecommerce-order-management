package com.ecommerce.payment_service.serviceImpl;

import com.ecommerce.payment_service.dto.PaymentDto;
import com.ecommerce.payment_service.entity.PaymentEntity;
import com.ecommerce.payment_service.repository.PaymentRepository;
import com.ecommerce.payment_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public PaymentDto processPayment(PaymentDto payment) {
        payment.setPaymentStatus("Success");
        PaymentEntity paymentEntity = dtoToEntity(payment);
        PaymentEntity savePayment = paymentRepository.save(paymentEntity);
        return entityToDto(savePayment);
    }

    @Override
    public PaymentDto getPaymentDetailsByOrderId(Long orderId) {
//        return paymentRepository.findByOrderId(orderId);
        return entityToDto(paymentRepository.findByOrderId(orderId));
    }

    @Override
    public Optional<PaymentEntity> getPaymentDetailsById(Long id) {
        return (paymentRepository.findById(id));
    }


    private PaymentDto entityToDto(PaymentEntity paymentEntity){
        return new PaymentDto(paymentEntity.getId(),
                paymentEntity.getOrderId(),
                paymentEntity.getPaymentStatus(),
                paymentEntity.getPaymentMethod(),
                paymentEntity.getAmount());
    }

    private PaymentEntity dtoToEntity(PaymentDto paymentDto){
        return new PaymentEntity(paymentDto.getId(),
                paymentDto.getOrderId(),
                paymentDto.getPaymentStatus(),
                paymentDto.getPaymentMethod(),
                paymentDto.getAmount());
    }
}
