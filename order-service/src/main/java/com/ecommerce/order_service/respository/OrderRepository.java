package com.ecommerce.order_service.respository;


import com.ecommerce.order_service.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository <OrderEntity,Long>{
        List<OrderEntity> findUserById(Long userId);
}
