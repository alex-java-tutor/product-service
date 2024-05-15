package com.example.productservice.dto;

import com.example.productservice.exception.ProductServiceException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SortBy {
    AZ(Sort.by(Sort.Direction.ASC, "name")),
    ZA(Sort.by(Sort.Direction.DESC, "name")),
    PRICE_ASC(Sort.by(Sort.Direction.ASC, "price")),
    PRICE_DESC(Sort.by(Sort.Direction.DESC, "price")),
    DATE_ASC(Sort.by(Sort.Direction.ASC, "createdAt")),
    DATE_DESC(Sort.by(Sort.Direction.DESC, "createdAt"));

    private final Sort sort;

    @JsonCreator
    public static SortBy fromString(String str) {
        try {
            return SortBy.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e) {
            var msg = "Failed to create SortBy from string: %s".formatted(str);
            throw new ProductServiceException(msg, HttpStatus.BAD_REQUEST);
        }
    }
}
