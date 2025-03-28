package com.apis.apiscollection.infrastructure.product.adapter.persistence;

import com.apis.apiscollection.application.product.port.out.ProductRepositoryPort;
import com.apis.apiscollection.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {
    private final ProductRepository productRepository;

    public ProductRepositoryAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findProductById(UUID id) {
        return productRepository.findById(id)
                .map(ProductEntityMapper::toDomain).orElseThrow(() -> new RuntimeException("Product not founded."));
    }

    @Transactional
    @Override
    public void saveProduct(Product product) {
        ProductEntity entity = ProductEntityMapper.toEntity(product);
        productRepository.save(entity);
    }

    @Transactional
    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findAllProducts(int page, int pageSize) {
        PageRequest pageable = PageRequest.of(page, pageSize);
        return productRepository.findAll(pageable).map(ProductEntityMapper::toDomain);
    }
}
