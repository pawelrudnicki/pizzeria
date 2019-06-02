package com.pizza.pizza_application.Services;

import com.pizza.pizza_application.Models.Order;
import com.pizza.pizza_application.Models.Product;
import java.util.List;

public interface IOrderService {
    Order findOne(Long id);
    List<Order> getAll();
    void createOrder(List<Product> products);
    void deleteOrder(Long id);
}
