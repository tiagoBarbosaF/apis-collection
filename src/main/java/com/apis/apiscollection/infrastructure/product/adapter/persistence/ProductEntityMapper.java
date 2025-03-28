package com.apis.apiscollection.infrastructure.product.adapter.persistence;

import com.apis.apiscollection.domain.product.Product;

public class ProductEntityMapper {
    public static ProductEntity toEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .sku(product.getSku())
                .stockQuantity(product.getStockQuantity())
                .category(product.getCategory())
                .brand(product.getBrand())
                .weight(product.getWeight())
                .height(product.getHeight())
                .width(product.getWidth())
                .length(product.getLength())
                .imageUrl(product.getImageUrl())
                .active(product.isActive())
                .build();
    }

    public static Product toDomain(ProductEntity entity) {
        return Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .sku(entity.getSku())
                .stockQuantity(entity.getStockQuantity())
                .category(entity.getCategory())
                .brand(entity.getBrand())
                .weight(entity.getWeight())
                .height(entity.getHeight())
                .width(entity.getWidth())
                .length(entity.getLength())
                .imageUrl(entity.getImageUrl())
                .active(entity.isActive())
                .build();
    }
}
