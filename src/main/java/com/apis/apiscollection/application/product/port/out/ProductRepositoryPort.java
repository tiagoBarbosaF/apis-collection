package com.apis.apiscollection.application.product.port.out;

import com.apis.apiscollection.domain.product.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ProductRepositoryPort {
    Product findProductById(UUID id);
    void saveProduct(Product product);
    void deleteProduct(UUID id);
    Page<Product> findAllProducts(int page, int pageSize);
    void saveListOfProducts(List<Product> products);
}
