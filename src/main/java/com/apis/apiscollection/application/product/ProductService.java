package com.apis.apiscollection.application.product;

import com.apis.apiscollection.application.dto.MessageResponse;
import com.apis.apiscollection.application.product.dto.ProductRequest;
import com.apis.apiscollection.application.product.dto.ProductResponse;
import com.apis.apiscollection.application.product.mapper.ProductMapper;
import com.apis.apiscollection.application.product.port.in.ProductUseCase;
import com.apis.apiscollection.application.product.port.out.ProductRepositoryPort;
import com.apis.apiscollection.domain.product.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService implements ProductUseCase {
    private final ProductRepositoryPort productRepositoryPort;
    private final ObjectMapper objectMapper;

    public ProductService(ProductRepositoryPort productRepositoryPort, ObjectMapper objectMapper) {
        this.productRepositoryPort = productRepositoryPort;
        this.objectMapper = objectMapper;
    }

    @Override
    public ProductResponse findProductById(UUID id) {
        Product productFind = productRepositoryPort.findProductById(id);
        return ProductMapper.mapDomainToResponse(productFind);
    }

    @Override
    public MessageResponse createProduct(ProductRequest request) {
        Product product = ProductMapper.mapRequestToDomain(request);
        productRepositoryPort.saveProduct(product);
        return new MessageResponse("Product created");
    }

    @Override
    public MessageResponse updateProduct(UUID id, ProductRequest request) {
        Product productFind = productRepositoryPort.findProductById(id);
        Product productToUpdate = ProductMapper.mapRequestToDomain(request);
        Product product = productFind.updateProduct(productToUpdate);

        productRepositoryPort.saveProduct(product);
        return new MessageResponse("Product updated");
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

    @Override
    public MessageResponse createListOfProducts(List<ProductRequest> productRequests) {
        List<Product> listProductsToSave = productRequests.stream().map(ProductMapper::mapRequestToDomain).toList();
        productRepositoryPort.saveListOfProducts(listProductsToSave);
        return new MessageResponse("List of Products created");
    }

    @Override
    public MessageResponse createListOfProductsByFile(MultipartFile file) {
        if (file.isEmpty()) return new MessageResponse("File is empty");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<Product> products = new ArrayList<>();
            String line;
            boolean firstLine = true;
            String[] headers = null;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    headers = line.split(",");
                    firstLine = false;
                    continue;
                }

                String[] values = line.split(",");
                String json = createJson(headers, values);

                Product product = objectMapper.readValue(json, Product.class);
                products.add(product);
            }
            productRepositoryPort.saveListOfProducts(products);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new MessageResponse("List of Products created using file!!");
    }

    private String createJson(String[] headers, String[] values) {
        StringBuilder json = new StringBuilder("{");
        for (int i = 0; i < headers.length; i++) {
            json.append("\"").append(headers[i]).append("\":\"").append(values[i]).append("\"");
            if (i < headers.length - 1) json.append(", ");
        }

        json.append("}");
        return json.toString();
    }
}
