package com.apis.apiscollection.infrastructure.product.adapter.persistence;

import com.apis.apiscollection.application.product.port.out.ProductRepositoryPort;
import com.apis.apiscollection.domain.product.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {
    private final ProductRepository productRepository;

    public ProductRepositoryAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void createProduct(Product product) {
        ProductEntity entity = ProductEntityMapper.toEntity(product);
        productRepository.save(entity);
    }
}
