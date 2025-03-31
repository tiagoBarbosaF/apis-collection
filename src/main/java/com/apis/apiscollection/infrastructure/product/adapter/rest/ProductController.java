package com.apis.apiscollection.infrastructure.product.adapter.rest;

import com.apis.apiscollection.application.dto.MessageResponse;
import com.apis.apiscollection.application.product.dto.ProductRequest;
import com.apis.apiscollection.application.product.dto.ProductResponse;
import com.apis.apiscollection.application.product.port.in.ProductUseCase;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ProductController implements ProductApi {
    private final ProductUseCase productUseCase;

    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @Override
    public ResponseEntity<MessageResponse> createProduct(@RequestBody @Valid ProductRequest request) {
        MessageResponse response = productUseCase.createProduct(request);
        return response != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<MessageResponse> updateProduct(@RequestParam(name = "product_id") UUID productId,
                                                         @RequestBody @Valid ProductRequest request) {
        MessageResponse response = productUseCase.updateProduct(productId, request);
        return response != null
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<ProductResponse> findProductById(@RequestParam(name = "product_id") UUID productId) {
        ProductResponse response = productUseCase.findProductById(productId);
        return response != null
                ? ResponseEntity.status(HttpStatus.OK).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<MessageResponse> deleteProduct(@RequestParam(name = "product_id") UUID productId) {
        MessageResponse response = productUseCase.deleteProduct(productId);
        return response != null
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<Page<ProductResponse>> findAllProducts(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int pageSize) {
        Page<ProductResponse> response = productUseCase.findAllProducts(page, pageSize);
        return response != null
                ? ResponseEntity.status(HttpStatus.OK).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
