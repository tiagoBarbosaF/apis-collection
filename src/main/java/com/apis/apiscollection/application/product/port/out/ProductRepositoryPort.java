package com.apis.apiscollection.application.product.port.out;

import com.apis.apiscollection.domain.product.Product;

public interface ProductRepositoryPort {
    void createProduct(Product product);
}
