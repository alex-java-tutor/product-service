package com.example.productservice.storage.repositories;

import com.example.productservice.BaseTest;
import com.example.productservice.storage.model.Product;
import com.example.productservice.storage.repositories.updaters.ProductUpdatersConfig;
import com.example.productservice.testutils.TestDataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.example.productservice.testutils.TestConstants.PRODUCT_ONE_NAME;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@Transactional(propagation = Propagation.NEVER)
@Import(ProductUpdatersConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest extends BaseTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void updateProduct_updatesProduct_whenAllFieldsAreSet() {
        var update = TestDataProvider.updateProductRequest();
        var id = getIdByName(PRODUCT_ONE_NAME);
        int updateCount = productRepository.updateProduct(id, update);
        assertThat(updateCount).isEqualTo(1);
        Product product = productRepository.findById(id).get();
        assertFieldsEquality(product, update, "name", "price", "description");
    }

}
