package com.example.spring_boot_crud_api.repository;

import com.example.spring_boot_crud_api.exception.ProductNotFoundException;
import com.example.spring_boot_crud_api.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(SpringExtension.class)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setName("MacBook Air M4");
        product.setPrice(49.999);
        product.setDescription("Apple MacBook 16GB 256GB SSD macOS 13 Taşınabilir Bilgisayar Gök Mavisi MC6T4TUA");
        product.setStock(5);
    }

    @DisplayName("Create Product Test")
    @Test
    public void shouldCreateProductSuccessfully() {


        Product savedProduct = productRepository.save(product);

        // assert
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo(product.getName());
        assertThat(savedProduct.getPrice()).isEqualTo(product.getPrice());
        assertThat(savedProduct.getDescription()).isEqualTo(product.getDescription());
        assertThat(savedProduct.getStock()).isEqualTo(product.getStock());

    }


    @Test
    public void shouldReturnProductById() {

        Product savedProduct = productRepository.save(product);

        // Act
        Product retrievedProduct = productRepository.findById(savedProduct.getId())
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id: " + savedProduct.getId()));


        // Assert
        assertThat(retrievedProduct).isNotNull();
        assertThat(retrievedProduct.getId()).isEqualTo(savedProduct.getId());
        assertThat(retrievedProduct.getName()).isEqualTo(savedProduct.getName());
        assertThat(retrievedProduct.getPrice()).isEqualTo(savedProduct.getPrice());
        assertThat(retrievedProduct.getDescription()).isEqualTo(savedProduct.getDescription());
        assertThat(retrievedProduct.getStock()).isEqualTo(savedProduct.getStock());
    }


    @Test
    public void shouldReturnProductNotFoundException() {
        // Arrange
        Long nonExistentProductId = 1L;

        // Act & Assert
        Assertions.assertThrows(ProductNotFoundException.class, () -> {
            productRepository.findById(nonExistentProductId)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + nonExistentProductId));
        });
    }

    @Test
    public void shouldUpdateProductSuccessfully() {
        // Arrange
        Product savedProduct = productRepository.save(product);

        // Act
        savedProduct.setName("Updated Product");
        savedProduct.setPrice(99.999);
        savedProduct.setDescription("Updated Description");
        savedProduct.setStock(10);

        Product updatedProduct = productRepository.save(savedProduct);


        Product retrievedProduct = productRepository.findById(updatedProduct.getId())
                .orElseThrow(
                        () -> new ProductNotFoundException("Product not found with id: " + updatedProduct.getId())
                );


        // Assert
        assertThat(retrievedProduct).isNotNull();
        assertThat(retrievedProduct.getId()).isEqualTo(savedProduct.getId());
        assertThat(retrievedProduct.getName()).isEqualTo("Updated Product");
        assertThat(retrievedProduct.getPrice()).isEqualTo(99.999);
        assertThat(retrievedProduct.getDescription()).isEqualTo("Updated Description");
        assertThat(retrievedProduct.getStock()).isEqualTo(10);
    }

    @Test
    public void shouldDeleteProductSuccessfully() {
        // Arrange
        Product savedProduct = productRepository.save(product);

        // Act
        productRepository.deleteById(savedProduct.getId());

        // Assert
        assertThat(productRepository.findById(savedProduct.getId())).isEmpty();
    }
}
