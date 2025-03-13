package com.example.spring_boot_crud_api.service;

import com.example.spring_boot_crud_api.dto.ProductRequestDto;
import com.example.spring_boot_crud_api.dto.ProductResponseDto;
import com.example.spring_boot_crud_api.exception.ProductNotFoundException;
import com.example.spring_boot_crud_api.mapper.ProductMapper;
import com.example.spring_boot_crud_api.model.Product;
import com.example.spring_boot_crud_api.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {


    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    private ProductRequestDto productRequestDto;
    private Product product;
    private ProductResponseDto productResponseDto;


    @BeforeEach
    void setUp() {
        productRequestDto = new ProductRequestDto();
        productRequestDto.setName("Test Product");
        productRequestDto.setPrice(100.0);
        productRequestDto.setDescription("Test Product Description");
        productRequestDto.setStock(10);

        product = new Product();
        product.setName("Test Product");
        product.setPrice(100.0);
        product.setDescription("Test Product Description");
        product.setStock(10);

        productResponseDto = new ProductResponseDto();
        productResponseDto.setName("Test Product");
        productResponseDto.setPrice(100.0);
        productResponseDto.setDescription("Test Product Description");
        productResponseDto.setStock(10);
    }

    @Test
    public void shouldCreateProductSuccessfully(){

        // Arrange
        when(productMapper.toEntity(productRequestDto)).thenReturn(product);
        when(productRepository.existsByName(product.getName())).thenReturn(false);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(productResponseDto);

        // Actual
        ProductResponseDto result = productService.createProduct(productRequestDto);

        // Assert
        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        assertEquals(100.0, result.getPrice());
        assertEquals("Test Product Description", result.getDescription());
        assertEquals(10, result.getStock());

        // verify
        verify(productRepository,times(1)).save(product);

    }


    @Test
    public void shouldReturnProductById(){

        // Arrange
        when (productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(productResponseDto);

        // Actual
        ProductResponseDto result = productService.getProductById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        assertEquals(100.0, result.getPrice());
        assertEquals("Test Product Description", result.getDescription());
        assertEquals(10, result.getStock());

        verify(productRepository).findById(1L);
        verify(productMapper).toDto(product);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {

        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () ->
                productService.getProductById(1L)
        );

        assertEquals("Product not found with id: 1", exception.getMessage());

        verify(productRepository).findById(1L);
        verify(productMapper, never()).toDto(any());
    }

}
