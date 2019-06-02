package com.pizza.pizza_application.Services;

import com.pizza.pizza_application.Models.Order;
import com.pizza.pizza_application.Models.Product;
import com.pizza.pizza_application.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findOne(Long id) {
        return this.orderRepository.findOneById(id);
    }

    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public void createOrder(List<Product> products) {
        Order order = new Order();
        order.setOrderProducts(products);
        BigDecimal price = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setOrderPrice(price);
        this.orderRepository.saveAndFlush(order);
        System.out.println(order);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = this.orderRepository.findOneById(id);
        this.orderRepository.delete(order);
    }
}
