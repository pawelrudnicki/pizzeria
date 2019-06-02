package com.pizza.pizza_application.Repository;

import com.pizza.pizza_application.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOneById(Long id);
}
