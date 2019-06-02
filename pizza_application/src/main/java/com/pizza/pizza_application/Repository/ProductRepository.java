package com.pizza.pizza_application.Repository;

import com.pizza.pizza_application.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findOneById(Long id);
}
