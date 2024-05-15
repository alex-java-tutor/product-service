package com.example.productservice.service;

import com.example.productservice.dto.*;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest dto);

    void deleteProduct(Long id);

    ProductResponse updateProduct(Long id, UpdateProductRequest update);

    ProductResponse getProduct(Long id);

    List<ProductResponse> getProducts(int from, int size, SortBy sort);

    OrderProductResponse getProductsForOrder(OrderProductRequest request);
}
