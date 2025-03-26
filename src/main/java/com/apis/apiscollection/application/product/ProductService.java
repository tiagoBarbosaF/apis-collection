package com.apis.apiscollection.application.product;

import com.apis.apiscollection.application.dto.MessageResponse;
import com.apis.apiscollection.application.product.dto.ProductRequest;
import com.apis.apiscollection.application.product.dto.ProductResponse;
import com.apis.apiscollection.application.product.mapper.ProductMapper;
import com.apis.apiscollection.application.product.port.in.ProductUseCase;
import com.apis.apiscollection.application.product.port.out.ProductRepositoryPort;
import com.apis.apiscollection.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService implements ProductUseCase {
    private final ProductRepositoryPort productRepositoryPort;

    public ProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public ProductResponse findProductById(UUID id) {
        Product productFind = productRepositoryPort.findProductById(id);
        return ProductMapper.mapDomainToResponse(productFind);
    }

    @Override
    public MessageResponse createProduct(ProductRequest request) {
        Product product = ProductMapper.mapRequestToDomain(request);
        productRepositoryPort.createProduct(product);

        return new MessageResponse("Product created");
    }

    @Override
    public MessageResponse deleteProduct(UUID id) {
        productRepositoryPort.deleteProduct(id);
        return new MessageResponse("Product deleted");
    }

    @Override
    public Page<ProductResponse> findAllProducts(int page, int pageSize) {
        Page<Product> allProducts = productRepositoryPort.findAllProducts(page, pageSize);
        return allProducts.map(ProductMapper::mapDomainToResponse);
    }
}
