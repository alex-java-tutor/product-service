package com.example.productservice.controller;

import com.example.productservice.dto.*;
import com.example.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ProductController", description = "REST API для работы с товарами.")
@Slf4j
@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "${api.product-create.summary}",
            description = "${api.product-create.description}"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "${api.response.createOk}"),
            @ApiResponse(
                    responseCode = "409",
                    description = "${api.response.createConflict}",
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "${api.response.createBadRequest}",
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class)
                    )),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody @Valid ProductRequest dto) {
        log.info("Received POST request to create Product: {}", dto);
        return productService.createProduct(dto);
    }

    @Operation(
            summary = "${api.product-delete.summary}",
            description = "${api.product-delete.description}"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "${api.response.deleteNoContent}")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") @Positive(message = "ID must be > 0.") Long id) {
        log.info("Received request to DELETE Product with ID={}", id);
        productService.deleteProduct(id);
    }

    @Operation(
            summary = "${api.product-update.summary}",
            description = "${api.product-update.description}"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response.updateOk}"),
            @ApiResponse(
                    responseCode = "404",
                    description = "${api.response.notFound}",
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "${api.response.updateBadRequest}",
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "${api.response.updateConflict}",
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @PatchMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable("id") @Positive(message = "ID must be > 0.") Long id,
                                         @RequestBody @Valid UpdateProductRequest update) {
        log.info("Received PATCH request to update product with ID={}. New Info: {}", id, update);
        return productService.updateProduct(id, update);
    }

    @Operation(
            summary = "${api.product-get.summary}",
            description = "${api.product-get.description}"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response.getOk}"),
            @ApiResponse(
                    responseCode = "404",
                    description = "${api.response.notFound}",
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable("id") @Positive(message = "ID must be > 0.") Long id) {
        log.info("Received request to GET Product with ID={}", id);
        return productService.getProduct(id);
    }

    @Operation(
            summary = "${api.product-list-get.summary}",
            description = "${api.product-list-get.description}"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response.getListOk}"),
            @ApiResponse(
                    responseCode = "400",
                    description = "${api.response.getListBadRequest}",
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @GetMapping
    public List<ProductResponse> getProducts(@RequestParam(value = "from", defaultValue = "0") @PositiveOrZero(message = "Page must be >= 0.") int from,
                                             @RequestParam(value = "size", defaultValue = "10") @Positive(message = "Page size must be > 0.") int size,
                                             @RequestParam(value = "sort", defaultValue = "az") @NotBlank(message = "Sort param must not be null or blank.") String sort) {
        log.info("Received request to GET list of products. Page: {}, pageSize: {}, sort: {}",
                from, size, sort);
        return productService.getProducts(from, size, SortBy.fromString(sort));
    }

    @Operation(
            summary = "${api.product-info.summary}",
            description = "${api.product-info.description}"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response.getProductInfoOk}"),
            @ApiResponse(
                    responseCode = "400",
                    description = "${api.response.getProductInfoBadRequest}",
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @PostMapping("/product-info")
    public OrderProductResponse getProductsForOrder(@RequestBody @Valid OrderProductRequest request) {
        log.info("Received POST request to get info for products with names: {}", request.getProductNames());
        return productService.getProductsForOrder(request);
    }

}
