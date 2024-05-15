package com.example.productservice.storage.repositories.updaters;

import com.example.productservice.dto.UpdateProductRequest;
import com.example.productservice.storage.model.Product_;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class ProductUpdatersConfig {

    @Bean
    ProductUpdater<String> descriptionUpdater() {
        return new ProductUpdater<>(Product_.description, UpdateProductRequest::getDescription);
    }

    @Bean
    ProductUpdater<String> nameUpdater() {
        return new ProductUpdater<>(Product_.name, UpdateProductRequest::getName);
    }

    @Bean
    ProductUpdater<BigDecimal> priceUpdater() {
        return new ProductUpdater<>(Product_.price, UpdateProductRequest::getPrice);
    }

}
