package com.ecommerce.order_service.service;


import com.ecommerce.order_service.DTO.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {

    OrderDto placeOrder(OrderDto orderDto);
    OrderDto getOrderById(Long id);
    List<OrderDto> getOrdersByUserId(Long userID);

    void deleteById(Long id);

}
