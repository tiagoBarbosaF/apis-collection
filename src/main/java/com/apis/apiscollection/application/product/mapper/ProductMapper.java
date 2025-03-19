package com.apis.apiscollection.application.product.mapper;

import com.apis.apiscollection.application.product.dto.ProductRequest;
import com.apis.apiscollection.domain.product.Product;

public class ProductMapper {
    public static Product mapRequestToDomain(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .sku(request.sku())
                .stockQuantity(request.stockQuantity())
                .category(request.category())
                .brand(request.brand())
                .weight(request.weight())
                .height(request.height())
                .width(request.width())
                .length(request.length())
                .imageUrl(request.imageUrl())
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
}
