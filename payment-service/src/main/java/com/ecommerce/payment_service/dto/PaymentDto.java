package com.ecommerce.payment_service.dto;



public class PaymentDto {

    private Long id;
    private Long orderId;
    private String paymentStatus;
    private String paymentMethod;
    private double amount;

    public PaymentDto(){

    }

    public PaymentDto(Long id, Long orderId, String paymentStatus, String paymentMethod, double amount) {
        this.id = id;
        this.orderId = orderId;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
