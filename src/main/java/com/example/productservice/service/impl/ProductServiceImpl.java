package com.example.productservice.service.impl;

import com.example.productservice.dto.*;
import com.example.productservice.exception.ProductServiceException;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.service.ProductService;
import com.example.productservice.storage.model.Product;
import com.example.productservice.storage.model.ProductProjection;
import com.example.productservice.storage.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper;
    private final ProductRepository repository;

    @Override
    public ProductResponse createProduct(ProductRequest dto) {
        var product = mapper.toDomain(dto);
        try {
            return mapper.toDto(repository.save(product));
        } catch (DataIntegrityViolationException e) {
            var msg = "Failed to create Product %s. Reason: Product with name %s already exists."
                    .formatted(dto, dto.getName());
            throw new ProductServiceException(msg, HttpStatus.CONFLICT);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ProductResponse updateProduct(Long id, UpdateProductRequest update) {
        try {
            int updateCount = repository.updateProduct(id, update);
            if (updateCount == 0) {
                var msg = "Product with ID=%d not found.".formatted(id);
                throw new ProductServiceException(msg, HttpStatus.NOT_FOUND);
            }
            return getProduct(id);
        } catch (DataIntegrityViolationException e) {
            var msg = "Failed to update Product with ID=%d. Reason: Product with name: %s already exists."
                    .formatted(id, update.getName());
            throw new ProductServiceException(msg, HttpStatus.CONFLICT);
        }
    }

    @Override
    public ProductResponse getProduct(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> {
                    var msg = "Product with ID=%d not found.".formatted(id);
                    return new ProductServiceException(msg, HttpStatus.NOT_FOUND);
                });
    }

    @Override
    public List<ProductResponse> getProducts(int from, int size, SortBy sort) {
        var pageable = PageRequest.of(from, size).withSort(sort.getSort());
        var page = repository.findAll(pageable);
        List<Product> products = page.get().toList();
        return mapper.toDtoList(products);
    }

    @Override
    public OrderProductResponse getProductsForOrder(OrderProductRequest request) {
        Map<String, ProductProjection> nameToProjection = repository.getProductInfosForNames(request.getProductNames()).stream()
                .collect(Collectors.toMap(ProductProjection::getName, Function.identity()));
        List<ProductInfo> productInfos = new ArrayList<>();
        for(String name: request.getProductNames()) {
            ProductInfo productInfo;
            if (nameToProjection.containsKey(name)) {
                var projection = nameToProjection.get(name);
                productInfo = new ProductInfo(projection.getName(), projection.getPrice(), true);
            } else {
                productInfo = new ProductInfo(name, null, false);
            }
            productInfos.add(productInfo);
        }
        return OrderProductResponse.builder()
                .productInfos(productInfos)
                .build();
    }
}
