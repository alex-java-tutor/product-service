package com.example.productservice.testutils;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.UpdateProductRequest;

import static com.example.productservice.testutils.TestConstants.*;

public class TestDataProvider {

    public static ProductRequest createProductRequest() {
        return ProductRequest.builder()
                .name(NEW_PRODUCT_NAME)
                .description(NEW_PRODUCT_DESCR)
                .price(NEW_PRODUCT_PRICE)
                .build();
    }

    public static UpdateProductRequest updateProductRequest() {
        return UpdateProductRequest
                .builder()
                .name(UPDATED_NAME)
                .description(UPDATED_DESCR)
                .price(UPDATED_PRICE)
                .build();
    }

}
