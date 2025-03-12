package com.ecommerce.order_service.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @GetMapping(value = "/test")
    public String testOrderService() {
        return "Order Service is working!";
    }
}
