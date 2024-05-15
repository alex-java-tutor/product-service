package com.example.productservice.storage.repositories;

import com.example.productservice.storage.model.Product;
import com.example.productservice.storage.model.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, CustomProductRepository {

    @Query("""
        select new com.example.productservice.storage.model.ProductProjection(
            p.name,
            p.price
        ) from Product  p where p.name in :names
    """)
    List<ProductProjection> getProductInfosForNames(@Param("names") List<String> names);

}
