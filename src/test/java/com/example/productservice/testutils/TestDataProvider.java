package com.example.productservice.testutils;

import com.example.productservice.dto.OrderProductRequest;
import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.UpdateProductRequest;

import java.util.List;

import static com.example.productservice.testutils.TestConstants.*;

public class TestDataProvider {

    public static OrderProductRequest orderProductRequest() {
        return OrderProductRequest.builder()
                .productNames(List.of(PRODUCT_ONE_NAME, PRODUCT_TWO_NAME, PRODUCT_THREE_NAME, NEW_PRODUCT_NAME))
                .build();
    }

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
