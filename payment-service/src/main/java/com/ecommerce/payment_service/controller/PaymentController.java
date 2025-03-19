package com.ecommerce.payment_service.controller;

import com.ecommerce.payment_service.dto.PaymentDto;
import com.ecommerce.payment_service.entity.PaymentEntity;
import com.ecommerce.payment_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<PaymentDto> processPayment(@RequestBody PaymentDto paymentDto){
        return new ResponseEntity<>(paymentService.processPayment(paymentDto), HttpStatus.CREATED);
    }

    @GetMapping("/id/{orderId}")
    public ResponseEntity<PaymentDto> getPaymentDetails(@PathVariable Long orderId){

        return new ResponseEntity<>(paymentService.getPaymentDetailsByOrderId(orderId),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<PaymentEntity>> getPaymentDetailsById(@PathVariable Long id){
        return new ResponseEntity<>(paymentService.getPaymentDetailsById(id),HttpStatus.OK);
    }
}
