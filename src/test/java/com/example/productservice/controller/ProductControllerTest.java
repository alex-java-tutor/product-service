package com.example.productservice.controller;

import com.example.productservice.BaseIntegTest;
import com.example.productservice.dto.OrderProductResponse;
import com.example.productservice.dto.ProductInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.example.productservice.testutils.TestConstants.*;
import static com.example.productservice.testutils.TestDataProvider.orderProductRequest;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@AutoConfigureMockMvc
public class ProductControllerTest extends BaseIntegTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getProductsForOrder_returnsCorrectInfo() {
        var url = BASE_URL + "/product-info";
        var request = orderProductRequest();

        webTestClient.post()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(OrderProductResponse.class)
                .value(response -> {
                    // new, one, three, two
                    var infos = response.getProductInfos()
                            .stream()
                            .sorted(Comparator.comparing(ProductInfo::getName))
                            .toList();

                    List<String> names = infos.stream().map(ProductInfo::getName).toList();
                    assertThat(names).containsExactlyElementsOf(List.of(NEW_PRODUCT_NAME, PRODUCT_ONE_NAME, PRODUCT_THREE_NAME, PRODUCT_TWO_NAME));

                    List<BigDecimal> prices = infos.stream().map(ProductInfo::getPrice).toList();
                    var expected = new ArrayList<BigDecimal>();
                    expected.add(null);
                    expected.addAll(List.of(PRODUCT_ONE_PRICE, PRODUCT_THREE_PRICE, PRODUCT_TWO_PRICE));
                    assertThat(prices).containsExactlyElementsOf(expected);

                    List<Boolean> available = infos.stream().map(ProductInfo::getIsAvailable).toList();
                    assertThat(available).containsExactlyElementsOf(List.of(false, true, true, true));
                });
    }

}
