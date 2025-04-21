package com.apis.apiscollection.application.product;

import com.apis.apiscollection.application.dto.MessageResponse;
import com.apis.apiscollection.application.product.dto.ProductRequest;
import com.apis.apiscollection.application.product.dto.ProductResponse;
import com.apis.apiscollection.application.product.port.out.ProductRepositoryPort;
import com.apis.apiscollection.domain.product.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.f4b6a3.uuid.UuidCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepositoryPort productRepositoryPort;
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private ProductService productService;

    @Test
    void test_findProductById() {
        UUID uuidMock = UuidCreator.getTimeOrderedEpoch();

        //Arrange
        Product productMock = mock(Product.class);
        when(productMock.getId()).thenReturn(uuidMock);
        when(productMock.getName()).thenReturn("Product Name Test");
        when(productMock.getPrice()).thenReturn(BigDecimal.TEN);

        when(productRepositoryPort.findProductById(uuidMock)).thenReturn(productMock);

        //Act
        ProductResponse response = productService.findProductById(uuidMock);

        //Assert
        assertNotNull(response);
        assertEquals(uuidMock, response.id());
        assertEquals("Product Name Test", response.name());
        assertEquals(BigDecimal.TEN, response.price());

        verify(productRepositoryPort, times(1)).findProductById(uuidMock);
    }

    @Test
    void test_createProduct() {
        //Arrange
        ProductRequest productRequestMock = createProductRequestMock();

        doNothing().when(productRepositoryPort).saveProduct(any(Product.class));

        //Act
        MessageResponse response = productService.createProduct(productRequestMock);

        //Assert
        assertNotNull(response);
        assertEquals("Product created", response.message());

        verify(productRepositoryPort, times(1)).saveProduct(any(Product.class));
    }

    @Test
    void test_updateProduct() {
        //Arrange
        UUID uuidMock = UuidCreator.getTimeOrderedEpoch();
        ProductRequest productRequestMock = createProductRequestMock();

        Product productMock = mock(Product.class);
        when(productRepositoryPort.findProductById(uuidMock)).thenReturn(productMock);
        doNothing().when(productRepositoryPort).saveProduct(any(Product.class));
        when(productMock.updateProduct(any(Product.class))).thenReturn(productMock);

        //Act
        MessageResponse response = productService.updateProduct(uuidMock, productRequestMock);

        //Assert
        assertNotNull(response);
        assertEquals("Product updated", response.message());

        verify(productRepositoryPort, times(1)).findProductById(uuidMock);
        verify(productRepositoryPort, times(1)).saveProduct(any(Product.class));
    }

    @Test
    void test_deleteProduct() {
        //Arrange
        UUID uuidMock = UuidCreator.getTimeOrderedEpoch();

        doNothing().when(productRepositoryPort).deleteProduct(uuidMock);

        //Act
        MessageResponse response = productService.deleteProduct(uuidMock);

        //Assert
        assertNotNull(response);
        assertEquals("Product deleted", response.message());

        verify(productRepositoryPort, times(1)).deleteProduct(uuidMock);
    }

    @Test
    void test_findAllProducts() {
        //Arrange
        int pageMock = 0;
        int pageSizeMock = 10;

        Product productMock = mock(Product.class);
        List<Product> productListMock = List.of(productMock, productMock);
        PageImpl<Product> productsPageMock = new PageImpl<>(productListMock);

        when(productRepositoryPort.findAllProducts(pageMock, pageSizeMock)).thenReturn(productsPageMock);

        //Act
        Page<ProductResponse> response = productService.findAllProducts(pageMock, pageSizeMock);

        //Assert
        assertNotNull(response);
        assertEquals(2, response.getTotalElements());

        verify(productRepositoryPort, times(1)).findAllProducts(pageMock, pageSizeMock);
    }

    @Test
    void test_createListOfProducts() {
        //Arrange
        ProductRequest productRequestMock = createProductRequestMock();
        List<ProductRequest> productRequestListMock = List.of(productRequestMock, productRequestMock);

        doNothing().when(productRepositoryPort).saveListOfProducts(anyList());

        //Act
        MessageResponse response = productService.createListOfProducts(productRequestListMock);

        //Assert
        assertNotNull(response);
        assertEquals("List of Products created", response.message());

        verify(productRepositoryPort, times(1)).saveListOfProducts(anyList());
    }

    @Test
    void test_createListOfProductsByFile() throws JsonProcessingException {
        //Arrange
        String csvContent = "name,price,description,sku\n"
                            + "Product 1,10.00,Description 1,Sku001\n"
                            + "Product 2,20.00,Descricao 2,SKU002\n";

        MockMultipartFile multipartFile = new MockMultipartFile(
                "file", "products.csv", "text/csv", csvContent.getBytes()
        );

        Product product1 = Product.builder()
                .name("Product 1")
                .price(BigDecimal.valueOf(10.00))
                .description("Description 1")
                .sku("Sku001")
                .build();

        Product product2 = Product.builder()
                .name("Product 2")
                .price(BigDecimal.valueOf(20.00))
                .description("Description 2")
                .sku("Sku002")
                .build();

        when(objectMapper.readValue(anyString(), eq(Product.class))).thenReturn(product1).thenReturn(product2);

        doNothing().when(productRepositoryPort).saveListOfProducts(anyList());

        //Act
        MessageResponse response = productService.createListOfProductsByFile(multipartFile);

        //Assert
        assertNotNull(response);
        assertEquals("List of Products created using file!!", response.message());

        verify(productRepositoryPort, times(1)).saveListOfProducts(argThat(list -> list.size() == 2));
        verify(objectMapper, times(2)).readValue(anyString(), eq(Product.class));

    }

    @Test
    void test_createListOfProductsByFile_emptyFile() {
        //Arrange
        MockMultipartFile emptyFile = new MockMultipartFile(
                "file",
                "empty.csv",
                "text/csv",
                new byte[0]
        );

        //Act
        MessageResponse response = productService.createListOfProductsByFile(emptyFile);

        //Assert
        assertNotNull(response);
        assertEquals("File is empty", response.message());

        verify(productRepositoryPort, never()).saveListOfProducts(any());
    }

    @Test
    void test_createListOfProductsByFile_throwsIOException() throws IOException {
        //Arrange
        MultipartFile fileMock = mock(MultipartFile.class);

        when(fileMock.isEmpty()).thenReturn(false);

        when(fileMock.getInputStream()).thenThrow(new IOException("Falha ao ler arquivo"));

        //Act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.createListOfProductsByFile(fileMock);
        });

        //Assert
        assertTrue(exception.getMessage().contains("Falha ao ler arquivo"));

        verify(productRepositoryPort, never()).saveListOfProducts(any());
    }

    private static ProductRequest createProductRequestMock() {
        ProductRequest productRequestMock = mock(ProductRequest.class);
        when(productRequestMock.name()).thenReturn("Product Name Test");
        when(productRequestMock.price()).thenReturn(BigDecimal.TEN);
        when(productRequestMock.description()).thenReturn("Product Description Test");
        when(productRequestMock.sku()).thenReturn("SKU123");
        when(productRequestMock.brand()).thenReturn("Brand123");
        when(productRequestMock.imageUrl()).thenReturn("imageUrl123");
        return productRequestMock;
    }
}