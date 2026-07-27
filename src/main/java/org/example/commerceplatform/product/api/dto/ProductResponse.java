package org.example.commerceplatform.product.api.dto;

import org.example.commerceplatform.product.domain.Product;

import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String name,
        Long price,
        Integer stockQuantity,
        LocalDateTime createdAt
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCreatedAt()
        );
    }
}
