package com.apis.apiscollection.infrastructure.product.adapter.rest;

import com.apis.apiscollection.application.dto.MessageResponse;
import com.apis.apiscollection.application.product.dto.ProductRequest;
import com.apis.apiscollection.application.product.dto.ProductResponse;
import com.apis.apiscollection.application.product.port.in.ProductUseCase;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController implements ProductApi {
    private final ProductUseCase productUseCase;

    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @Override
    public ResponseEntity<MessageResponse> createProduct(ProductRequest request) {
        MessageResponse response = productUseCase.createProduct(request);
        return response != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<MessageResponse> updateProduct(UUID productId, ProductRequest request) {
        MessageResponse response = productUseCase.updateProduct(productId, request);
        return response != null
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<ProductResponse> findProductById(UUID productId) {
        ProductResponse response = productUseCase.findProductById(productId);
        return response != null
                ? ResponseEntity.status(HttpStatus.OK).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<MessageResponse> deleteProduct(UUID productId) {
        MessageResponse response = productUseCase.deleteProduct(productId);
        return response != null
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<Page<ProductResponse>> findAllProducts(int page, int pageSize) {
        Page<ProductResponse> response = productUseCase.findAllProducts(page, pageSize);
        return response != null
                ? ResponseEntity.status(HttpStatus.OK).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @Override
    public ResponseEntity<MessageResponse> createListProducts(List<ProductRequest> products) {
        MessageResponse response = productUseCase.createListOfProducts(products);
        return response != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
