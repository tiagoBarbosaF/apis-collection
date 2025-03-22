package com.apis.apiscollection.application.product.port.in;

import com.apis.apiscollection.application.dto.MessageResponse;
import com.apis.apiscollection.application.product.dto.ProductRequest;
import com.apis.apiscollection.application.product.dto.ProductResponse;

import java.util.UUID;

public interface ProductUseCase {
    ProductResponse findProductById(UUID id);
    MessageResponse createProduct(ProductRequest request);
    MessageResponse deleteProduct(UUID id);
}
