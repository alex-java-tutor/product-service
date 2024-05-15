package com.example.productservice.service.impl;

import com.example.productservice.BaseIntegTest;
import com.example.productservice.service.ProductService;
import com.example.productservice.storage.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceImpTest extends BaseIntegTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

}
