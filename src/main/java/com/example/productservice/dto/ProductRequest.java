package com.example.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductRequest {
    @NotBlank(message = "Product name must not be null or blank.")
    private String name;
    @NotBlank(message = "Product description must not be null or blank.")
    private String description;
    @NotNull(message = "Product price must not be null.")
    @Positive(message = "Product price must be > 0.")
    private BigDecimal price;
}
