package com.example.productservice.controller;

import com.example.productservice.BaseIntegTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureMockMvc
public class ProductControllerTest extends BaseIntegTest {

    @Autowired
    private WebTestClient webTestClient;

}
