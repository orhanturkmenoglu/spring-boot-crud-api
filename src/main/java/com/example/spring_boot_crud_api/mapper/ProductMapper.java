package com.example.spring_boot_crud_api.mapper;

import com.example.spring_boot_crud_api.dto.ProductRequestDto;
import com.example.spring_boot_crud_api.dto.ProductResponseDto;
import com.example.spring_boot_crud_api.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    // ProductRequestDto'dan Product'a dönüşüm
    public Product toEntity(ProductRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setDescription(dto.getDescription());
        return product;
    }

    // Product'tan ProductResponseDto'ya dönüşüm
    public ProductResponseDto toDto(Product product) {
        if (product == null) {
            return null;
        }

        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setDescription(product.getDescription());
        return dto;
    }
}
