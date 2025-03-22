package com.apis.apiscollection.infrastructure.product.adapter.rest;

import com.apis.apiscollection.application.dto.MessageResponse;
import com.apis.apiscollection.application.person.dto.PersonResponse;
import com.apis.apiscollection.application.product.dto.ProductRequest;
import com.apis.apiscollection.application.product.dto.ProductResponse;
import com.apis.apiscollection.application.product.port.in.ProductUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/product")
@Tag(name = "Product", description = "Manage product operations")
public class ProductController {
    private final ProductUseCase productUseCase;

    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @PostMapping
    @Operation(summary = "Create a new product", description = "Creates a new product and returns the create product message.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<MessageResponse> createProduct(@RequestBody @Valid ProductRequest request) {
        MessageResponse response = productUseCase.createProduct(request);
        return response != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/id")
    @Operation(summary = "Get a product by ID", description = "Find a product using the ID reference.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<ProductResponse> findProductById(@RequestParam(name = "product_id") UUID productId) {
        ProductResponse response = productUseCase.findProductById(productId);
        return response != null
                ? ResponseEntity.status(HttpStatus.OK).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping
    @Operation(summary = "Delete product", description = "Delete product by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<MessageResponse> deleteProduct(@RequestParam(name = "product_id") UUID productId) {
        MessageResponse response = productUseCase.deleteProduct(productId);
        return response != null
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
