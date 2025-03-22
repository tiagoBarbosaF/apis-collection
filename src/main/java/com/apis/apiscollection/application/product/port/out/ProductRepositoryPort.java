package com.apis.apiscollection.application.product.port.out;

import com.apis.apiscollection.domain.product.Product;

import java.util.UUID;

public interface ProductRepositoryPort {
    Product findProductById(UUID id);
    void createProduct(Product product);
    void deleteProduct(UUID id);
}
