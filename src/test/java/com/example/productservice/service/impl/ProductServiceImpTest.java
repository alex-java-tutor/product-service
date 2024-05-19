package com.example.productservice.service.impl;

import com.example.productservice.BaseIntegTest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.service.ProductService;
import com.example.productservice.storage.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static com.example.productservice.testutils.TestDataProvider.createProductRequest;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ProductServiceImpTest extends BaseIntegTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void createProduct_createsProduct() {
        var request = createProductRequest();
        var now = LocalDateTime.now().minusNanos(1000);
        ProductResponse response = productService.createProduct(request);
        assertFieldsEquality(request, response, "name", "description", "price");
        assertThat(response.getId()).isNotNull();
        assertThat(response.getCreatedAt()).isAfter(now);
        assertThat(response.getUpdatedAt()).isAfter(now);
    }

}
