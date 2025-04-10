package com.ecommerce.product_service.serviceImpl;

import com.ecommerce.product_service.DTO.ProductDto;
import com.ecommerce.product_service.customException.OutOfStockException;
import com.ecommerce.product_service.customException.ProductNotFoundException;
import com.ecommerce.product_service.entity.ProductEntity;
import com.ecommerce.product_service.repository.ProductRepository;
import com.ecommerce.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        ProductEntity product = dtoToEntity(productDto);
        ProductEntity saveProduct = productRepository.save(product);

        return entityToDTO(saveProduct);
    }

    @Override
    public List<ProductDto> getAllProducts() {


        return productRepository.findAll().stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        return productRepository.findById(id).map(this::entityToDTO)
                .orElseThrow(()->new ProductNotFoundException("Product not found with the following id"+ id));
    }

    @Override
    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> searchProducts(String keyword) {
        List<ProductEntity> products = productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword,keyword);
        return products.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public void reduceStock(Long productId, int quantity) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));

        if (product.getStock() < quantity) {
            throw new OutOfStockException("Not enough stock for product " + productId);
        }

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }

    // 🔹 Convert Entity to DTO
    private ProductDto entityToDTO(ProductEntity product) {

        return new ProductDto(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getStock());
    }

    // 🔹 Convert DTO to Entity
    private ProductEntity dtoToEntity(ProductDto productDTO) {
        return new ProductEntity(productDTO.getId(),
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getQuantity(),
                productDTO.getStock());

//        return ProductEntity.builder()
//                .id(productDTO.getId())
//                .name(productDTO.getName())
//                .description(productDTO.getDescription())
//                .price(productDTO.getPrice())
//                .quantity(productDTO.getQuantity())
//                .build();
    }
}
