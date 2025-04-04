package com.apis.apiscollection.application.product.port.in;

import com.apis.apiscollection.application.dto.MessageResponse;
import com.apis.apiscollection.application.product.dto.ProductRequest;
import com.apis.apiscollection.application.product.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductUseCase {
    ProductResponse findProductById(UUID id);
    MessageResponse createProduct(ProductRequest request);
    MessageResponse updateProduct(UUID id, ProductRequest request);
    MessageResponse deleteProduct(UUID id);
    Page<ProductResponse> findAllProducts(int page, int pageSize);
    MessageResponse createListOfProducts(List<ProductRequest> productRequests);
    MessageResponse createListOfProductsByFile(MultipartFile file);
}
