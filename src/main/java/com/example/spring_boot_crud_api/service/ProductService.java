package com.example.spring_boot_crud_api.service;

import com.example.spring_boot_crud_api.dto.ProductRequestDto;
import com.example.spring_boot_crud_api.dto.ProductResponseDto;
import com.example.spring_boot_crud_api.exception.ProductNotFoundException;
import com.example.spring_boot_crud_api.mapper.ProductMapper;
import com.example.spring_boot_crud_api.model.Product;
import com.example.spring_boot_crud_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    // Tüm ürünleri listelemek
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    // ID'ye göre ürün almak
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return productMapper.toDto(product);
    }

    // Yeni ürün oluşturmak
    public ProductResponseDto createProduct(ProductRequestDto productCreateDto) {
        // Dönüşüm işlemi
        Product product = productMapper.toEntity(productCreateDto);

        // Ürünün ismiyle zaten var olup olmadığını kontrol etme
        if (productRepository.existsByName(product.getName())) {
            throw new IllegalArgumentException("Product with name: " + product.getName() + " already exists");
        }

        // Ürünü kaydetme
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    // Ürün güncellemek
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productUpdateDto) {
        // Ürünü bulma
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        // Ürün bilgilerini güncelleme
        product.setName(productUpdateDto.getName());
        product.setPrice(productUpdateDto.getPrice());
        product.setStock(productUpdateDto.getStock());

        // Güncellenmiş ürünü kaydetme
        Product updatedProduct = productRepository.save(product);
        return productMapper.toDto(updatedProduct);
    }

    // Ürün silmek
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }
}
