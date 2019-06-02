package com.pizza.pizza_application.Controllers;

import com.pizza.pizza_application.Models.Product;
import com.pizza.pizza_application.Services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return this.productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return this.productService.getProduct(id);
    }

    @PostMapping
    public void createProduct(@RequestBody Product product) {
        this.productService.createProduct(product.getName(), product.getPrice());
    }

    @DeleteMapping("/{id}")
    public void removeProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
    }
}
