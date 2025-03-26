package com.apis.apiscollection.application.product.mapper;

import com.apis.apiscollection.application.product.dto.ProductRequest;
import com.apis.apiscollection.application.product.dto.ProductResponse;
import com.apis.apiscollection.domain.product.Product;

public class ProductMapper {
    public static Product mapRequestToDomain(ProductRequest request) {
        return Product.builder()
                .name(request.name().isEmpty() ? null : request.name())
                .description(request.description().isEmpty() ? null : request.description())
                .price(request.price())
                .sku(request.sku().isEmpty() ? null : request.sku())
                .stockQuantity(request.stockQuantity())
                .category(request.category())
                .brand(request.brand().isEmpty() ? null : request.brand())
                .weight(request.weight())
                .height(request.height())
                .width(request.width())
                .length(request.length())
                .imageUrl(request.imageUrl().isEmpty() ? null : request.imageUrl())
                .active(request.active())
                .build();
    }

    public static ProductRequest mapDomainToRequest(Product product) {
        return ProductRequest.builder()
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

    public static ProductResponse mapDomainToResponse(Product product) {
        return ProductResponse.builder()
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
}
