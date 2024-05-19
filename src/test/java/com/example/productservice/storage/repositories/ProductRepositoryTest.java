package com.example.productservice.storage.repositories;

import com.example.productservice.BaseTest;
import com.example.productservice.storage.model.Product;
import com.example.productservice.storage.repositories.updaters.ProductUpdatersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.example.productservice.testutils.TestConstants.PRODUCT_ONE_NAME;
import static com.example.productservice.testutils.TestConstants.PRODUCT_TWO_NAME;
import static com.example.productservice.testutils.TestDataProvider.updateProductRequest;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Transactional(propagation = Propagation.NEVER)
@Import(ProductUpdatersConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest extends BaseTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void updateProduct_failsWithDataIntegrityViolationException_whenNameIsNotUnique() {
        var update = updateProductRequest();
        update.setName(PRODUCT_ONE_NAME);

        var id = getIdByName(PRODUCT_TWO_NAME);
        assertThrows(DataIntegrityViolationException.class,
                () -> productRepository.updateProduct(id, update));
    }

    @Test
    void updateProduct_updatesProduct_whenAllFieldsAreSet() {
        var update = updateProductRequest();
        var id = getIdByName(PRODUCT_ONE_NAME);
        int updateCount = productRepository.updateProduct(id, update);
        assertThat(updateCount).isEqualTo(1);
        Product product = productRepository.findById(id).get();
        assertFieldsEquality(product, update, "name", "price", "description");
    }

}
