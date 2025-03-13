package com.ecommerce.order_service.serviceImpl;

import com.ecommerce.order_service.DTO.OrderDto;
import com.ecommerce.order_service.entity.OrderEntity;
import com.ecommerce.order_service.respository.OrderRepository;
import com.ecommerce.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public OrderDto placeOrder(OrderDto orderDto) {
        OrderEntity orderEntity = dtoToEntity(orderDto);
        OrderEntity saveOrder = orderRepository.save(orderEntity);
        return entityToDto(saveOrder);
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return orderRepository.findById(id).map(this::entityToDto)
                .orElseThrow(()-> new RuntimeException("Not Found"));
        //or we can use optional so it can take null
    }


    @Override
    public List<OrderDto> getOrdersByUserId(Long userID) {
        return orderRepository.findUserById(userID)
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }


    private OrderEntity dtoToEntity(OrderDto orderDto){
        return new OrderEntity(orderDto.getId(),
                orderDto.getUserId(),
                orderDto.getProductId(),
                orderDto.getQuantity(),
                orderDto.getTotalPrice(),
                LocalDateTime.now());
    }
    private OrderDto entityToDto(OrderEntity orderEntity){

        return new OrderDto(orderEntity.getId(),
                orderEntity.getUserId(),
                orderEntity.getProductId(),
                orderEntity.getQuantity(),
                orderEntity.getTotalPrice(),
                orderEntity.getOrderDate());

    }
}
