package com.pizza.pizza_application.Services;

import com.pizza.pizza_application.Models.Product;
import com.pizza.pizza_application.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(Long id) {
        return this.productRepository.findOneById(id);
    }

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public void createProduct(String name, BigDecimal price) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        this.productRepository.saveAndFlush(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = this.productRepository.findOneById(id);
        this.productRepository.delete(product);
    }
}
