package com.example.productservice.storage.repositories;

import com.example.productservice.dto.UpdateProductRequest;
import com.example.productservice.storage.model.Product;
import com.example.productservice.storage.model.Product_;
import com.example.productservice.storage.repositories.updaters.ProductUpdater;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomProductRepositoryImpl implements CustomProductRepository {

    private final EntityManager em;
    private final List<ProductUpdater<?>> updaters;

    @Transactional
    @Override
    public int updateProduct(Long productId, UpdateProductRequest dto) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Product> update = cb.createCriteriaUpdate(Product.class);
        Root<Product> root = update.from(Product.class);
        updaters.forEach(updater -> updater.updateProduct(update, dto));
        update.where(cb.equal(root.get(Product_.ID), productId));
        return em.createQuery(update).executeUpdate();
    }
}
