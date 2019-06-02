package com.pizza.pizza_application.Services;

import com.pizza.pizza_application.Models.Product;

import java.math.BigDecimal;
import java.util.List;

public interface IProductService {
    Product getProduct(Long id);
    List<Product> getAll();
    void createProduct(String name, BigDecimal price);
    void deleteProduct(Long id);
}
