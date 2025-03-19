package com.apis.apiscollection.application.product;

import com.apis.apiscollection.application.dto.MessageResponse;
import com.apis.apiscollection.application.product.dto.ProductRequest;
import com.apis.apiscollection.application.product.mapper.ProductMapper;
import com.apis.apiscollection.application.product.port.in.ProductUseCase;
import com.apis.apiscollection.application.product.port.out.ProductRepositoryPort;
import com.apis.apiscollection.domain.product.Product;
import com.github.f4b6a3.uuid.UuidCreator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService implements ProductUseCase {
    private final ProductRepositoryPort productRepositoryPort;

    public ProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public MessageResponse createProduct(ProductRequest request) {
        UUID timeOrderedEpoch = UuidCreator.getTimeOrderedEpoch();
        System.out.println(timeOrderedEpoch);

        Product product = ProductMapper.mapRequestToDomain(request);
        productRepositoryPort.createProduct(product);

        return new MessageResponse("Product created");
    }
}
