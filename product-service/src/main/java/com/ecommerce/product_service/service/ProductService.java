package com.ecommerce.product_service.service;

import com.ecommerce.product_service.DTO.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    ProductDto createProduct(ProductDto productDto);
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    void deleteProduct(Long id);

    List<ProductDto> searchProducts(String keyword);

    public void reduceStock(Long productId, int quantity);

}
