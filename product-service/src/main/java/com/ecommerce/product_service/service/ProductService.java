package com.ecommerce.product_service.service;

import com.ecommerce.product_service.DTO.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);
    List<ProductDto> getAllProducts();
    Optional<ProductDto> getProductById(Long id);
    void deleteProduct(Long id);

}
