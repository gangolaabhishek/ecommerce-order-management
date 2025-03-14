package com.ecommerce.order_service.service;


import com.ecommerce.order_service.DTO.OrderDto;
import com.ecommerce.order_service.DTO.OrderRequest;
import com.ecommerce.order_service.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {

    OrderEntity placeOrder(OrderRequest orderRequest);
    OrderDto placeOrder(OrderDto orderDto);
    OrderDto getOrderById(Long id);
    List<OrderDto> getOrdersByUserId(Long userID);

    void deleteById(Long id);

}
