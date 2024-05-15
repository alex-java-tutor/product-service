package com.example.productservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderProductResponse {
    private List<ProductInfo> productInfos;
}
