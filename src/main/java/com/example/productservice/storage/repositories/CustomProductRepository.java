package com.example.productservice.storage.repositories;

import com.example.productservice.dto.UpdateProductRequest;

public interface CustomProductRepository {

    int updateProduct(Long productId, UpdateProductRequest dto);

}
