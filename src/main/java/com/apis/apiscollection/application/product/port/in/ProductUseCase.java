package com.apis.apiscollection.application.product.port.in;

import com.apis.apiscollection.application.dto.MessageResponse;
import com.apis.apiscollection.application.product.dto.ProductRequest;

public interface ProductUseCase {
    MessageResponse createProduct(ProductRequest request);
}
