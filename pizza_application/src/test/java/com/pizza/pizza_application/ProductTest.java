package com.pizza.pizza_application;

import com.pizza.pizza_application.Models.Product;
import com.pizza.pizza_application.Repository.ProductRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    public void itShouldReturnsCorrectProductName() {
        Long id = 99L;
        String name = "testowyProdukt";
        BigDecimal price = new BigDecimal("19.99");
        Product product = new Product(id, name, price);

        entityManager.persist(product);
        entityManager.flush();

        Product found = productRepository.findOneById(id);

        assertThat(found.getName()).isEqualTo(product.getName());
    }
}
