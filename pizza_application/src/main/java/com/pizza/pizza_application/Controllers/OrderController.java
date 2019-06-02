package com.pizza.pizza_application.Controllers;

import com.pizza.pizza_application.Models.Order;
import com.pizza.pizza_application.Models.Product;
import com.pizza.pizza_application.Services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderController {

    private IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders() {
        return this.orderService.getAll();
    }

    @GetMapping("/{id}/products")
    public List<Product> getProductsFromOrder(@PathVariable Long id) {
        Order order = this.orderService.findOne(id);
        return order.getOrderProducts();
    }

    @RequestMapping("/{id}")
    @GetMapping
    public Order getOrder(@PathVariable Long id) {
        return this.orderService.findOne(id);
    }

    @PostMapping
    public void createOrder(@RequestBody List<Product> products) {
        this.orderService.createOrder(products);
    }

    @DeleteMapping("/{id}")
    public void removeOrder(@PathVariable Long id) {
        this.orderService.deleteOrder(id);
    }
}
