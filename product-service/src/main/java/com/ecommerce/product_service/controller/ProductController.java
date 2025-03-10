package com.ecommerce.product_service.controller;


import com.ecommerce.product_service.DTO.ProductDto;
import com.ecommerce.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDTO) {
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    @GetMapping("/searchProducts/{keyword}")
    public ResponseEntity<List<ProductDto>> searchProducts(@PathVariable String keyword){
        return new ResponseEntity<>(productService.searchProducts(keyword),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return new ResponseEntity<>(this.productService.getAllProducts(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductDto>> getProductById(@PathVariable Long id) {
        Optional<ProductDto> productDTO = productService.getProductById(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
