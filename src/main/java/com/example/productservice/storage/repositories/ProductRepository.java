package com.example.productservice.storage.repositories;

import com.example.productservice.storage.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
