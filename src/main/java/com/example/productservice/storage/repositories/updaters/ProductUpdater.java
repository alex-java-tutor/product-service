package com.example.productservice.storage.repositories.updaters;

import com.example.productservice.dto.UpdateProductRequest;
import com.example.productservice.storage.model.Product;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.metamodel.SingularAttribute;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class ProductUpdater<VALUE> {
    SingularAttribute<Product, VALUE> attr;
    Function<UpdateProductRequest, VALUE> valueExtractor;

    public void updateProduct(CriteriaUpdate<Product> update, UpdateProductRequest dto) {
        VALUE value = valueExtractor.apply(dto);
        if (value != null) {
            update.set(attr, value);
        }
    }
}
