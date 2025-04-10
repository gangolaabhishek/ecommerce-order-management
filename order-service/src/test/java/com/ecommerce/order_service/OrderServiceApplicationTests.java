package com.ecommerce.order_service;

import com.ecommerce.order_service.DTO.OrderRequest;
import com.ecommerce.order_service.DTO.ProductDto;
import com.ecommerce.order_service.entity.OrderEntity;
import com.ecommerce.order_service.respository.OrderRepository;
import com.ecommerce.order_service.serviceImpl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class OrderServiceApplicationTests {

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private OrderServiceImpl orderService;

	@Test
	public void testPlaceOrder_Success() {
		// Given
		OrderRequest orderRequest = new OrderRequest(1L, 101L, 2);
		ProductDto productDto = new ProductDto(1L, "Laptop", 1200.99);

		Mockito.when(restTemplate.getForObject("http://PRODUCT-SERVICE/products/1", ProductDto.class))
				.thenReturn(productDto);

		OrderEntity savedOrder = new OrderEntity(5001L, 1L, 101L, 2, 1000.0, "PENDING");
		Mockito.when(orderRepository.save(any(OrderEntity.class))).thenReturn(savedOrder);

		//When
		OrderEntity response = orderService.placeOrder(orderRequest);

		// Then
		assertNotNull(response);
		assertEquals(1000.0, response.getTotalPrice());
//		assertEquals("PENDING", response.getStatus());

	}

}
