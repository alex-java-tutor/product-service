package com.example.productservice.testutils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

public class TestConstants {

    public static final String BASE_URL = "/v1/products";

    public static final String PRODUCT_ONE_NAME = "Product One";
    public static final String PRODUCT_TWO_NAME = "Product Two";
    public static final String PRODUCT_THREE_NAME = "Product Three";
    public static final String PRODUCT_FOUR_NAME = "Product Four";
    public static final String PRODUCT_FIVE_NAME = "Product Five";
    public static final String NEW_PRODUCT_NAME = "New Product";


    public static final String PRODUCT_ONE_DESCR = "Description One";
    public static final String PRODUCT_TWO_DESCR = "Description Two";
    public static final String PRODUCT_THREE_DESCR = "Description Three";
    public static final String PRODUCT_FOUR_DESCR = "Description Four";
    public static final String PRODUCT_FIVE_DESCR = "Description Five";
    public static final String NEW_PRODUCT_DESCR = "New Description";

    public static final BigDecimal PRODUCT_ONE_PRICE = BigDecimal.valueOf(11.11);
    public static final BigDecimal PRODUCT_TWO_PRICE = BigDecimal.valueOf(12.12);
    public static final BigDecimal PRODUCT_THREE_PRICE = BigDecimal.valueOf(13.13);
    public static final BigDecimal PRODUCT_FOUR_PRICE = BigDecimal.valueOf(14.14);
    public static final BigDecimal PRODUCT_FIVE_PRICE = BigDecimal.valueOf(15.15);
    public static final BigDecimal NEW_PRODUCT_PRICE = BigDecimal.valueOf(16.16);

    public static final LocalDateTime PRODUCT_ONE_DATE = LocalDateTime.of(2024, Month.MAY, 11, 10, 23, 54);
    public static final LocalDateTime PRODUCT_TWO_DATE = LocalDateTime.of(2024, Month.MAY, 12, 10, 23, 54);
    public static final LocalDateTime PRODUCT_THREE_DATE = LocalDateTime.of(2024, Month.MAY, 13, 10, 23, 54);
    public static final LocalDateTime PRODUCT_FOUR_DATE = LocalDateTime.of(2024, Month.MAY, 14, 10, 23, 54);
    public static final LocalDateTime PRODUCT_FIVE_DATE = LocalDateTime.of(2024, Month.MAY, 15, 10, 23, 54);

    public static final String UPDATED_NAME = "New Name";
    public static final String UPDATED_DESCR = "New Description";
    public static final BigDecimal UPDATED_PRICE = BigDecimal.valueOf(99.99);

}
