package com.example.productservice.testutils;

import com.example.productservice.dto.UpdateProductRequest;

import java.math.BigDecimal;

import static com.example.productservice.testutils.TestConstants.*;

public class TestDataProvider {

    public static UpdateProductRequest updateProductRequest() {
        return UpdateProductRequest
                .builder()
                .name(NEW_NAME)
                .description(NEW_DESCR)
                .price(NEW_PRICE)
                .build();
    }

}
