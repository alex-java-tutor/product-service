package com.example.productservice.storage.repositories;

import com.example.productservice.BaseTest;
import com.example.productservice.storage.repositories.updaters.ProductUpdatersConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Transactional(propagation = Propagation.NEVER)
@Import(ProductUpdatersConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest extends BaseTest {

    @Autowired
    private ProductRepository productRepository;

}
