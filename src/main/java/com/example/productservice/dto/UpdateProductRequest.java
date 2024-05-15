package com.example.productservice.dto;

import com.example.productservice.dto.validation.NullOrNotBlank;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateProductRequest {
    @NullOrNotBlank(message = "Updated product name must not be blank.")
    private String name;
    @NullOrNotBlank(message = "Updated product description must not be blank.")
    private String description;
    @Positive(message = "Updated product price must be > 0.")
    private BigDecimal price;
}
