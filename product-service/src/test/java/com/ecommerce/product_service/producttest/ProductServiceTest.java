package com.ecommerce.product_service.producttest;

import com.ecommerce.product_service.DTO.ProductDto;
import com.ecommerce.product_service.customException.ProductNotFoundException;
import com.ecommerce.product_service.entity.ProductEntity;
import com.ecommerce.product_service.repository.ProductRepository;
import com.ecommerce.product_service.serviceImpl.ProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private ProductEntity sampleProduct;
    private ProductDto sampleDto;

    @BeforeEach
    void setup(){
        sampleProduct = new ProductEntity(1L, "Laptop", "Gaming Laptop", 120000.0, 15,30);
        sampleDto = new ProductDto(1L, "Laptop", "Gaming Laptop", 120000.0, 15,30);

          }

    @AfterEach
    void tearDown(){
        sampleProduct=null;
        sampleDto=null;
    }

    @Test
    public void testCreateProduct(){
        when(productRepository.save(any(ProductEntity.class))).thenReturn(sampleProduct);

        ProductDto result = productService.createProduct(sampleDto);
        assertNotNull(result);
        assertEquals("Laptop",result.getName());
        verify(productRepository,times(1)).save(any(ProductEntity.class));
    }

    @Test
    public void testGetAllProduct(){
        when(productRepository.findAll()).thenReturn(List.of(sampleProduct));

        List<ProductDto> result = productService.getAllProducts();
        assertEquals(1,result.size());
        assertEquals("Laptop",result.get(0).getName());
        verify(productRepository,times(1)).findAll();
    }

    @Test
    public void testGetProductById(){
        when(productRepository.findById(1L)).thenReturn(Optional.of(sampleProduct));

        ProductDto result = productService.getProductById(1L);

        assertEquals("Laptop", result.getName());
        assertEquals(1L,result.getId());
        verify(productRepository,times(1)).findById(1L);
    }

    @Test
    public void testGetProductByIdNotFound(){
        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,()->productService.getProductById(2L));
    }

    @Test
    public void testDeleteUserById(){
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteProduct(1L);
        verify(productRepository,times(1)).deleteById(1L);
    }

    @Test
    public void testSearchProduct(){
        ProductEntity product1 = new ProductEntity(1L, "Laptop", "Gaming beast", 120000.0, 10,30);
        ProductEntity product2 = new ProductEntity(2L, "Keyboard", "Mechanical keyboard", 3000.0, 20,30);

        when(productRepository
                .findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase
                        ("gaming","gaming")).thenReturn(List.of(product1,product2));
        List<ProductDto> result = productService.searchProducts("gaming");
        assertEquals(2,result.size());
        assertTrue(result.get(0).getName().toLowerCase().contains("laptop") || result.get(0).getDescription().toLowerCase().contains("gaming"));
        verify(productRepository, times(1)).findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase("gaming", "gaming");

    }


}
