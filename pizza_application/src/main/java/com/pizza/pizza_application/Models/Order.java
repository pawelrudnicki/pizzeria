package com.pizza.pizza_application.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "PRODUCTS_ORDERS",
            joinColumns = @JoinColumn(name = "idOrder"),
            inverseJoinColumns = @JoinColumn(name = "idProduct"))
    private List<Product> orderProducts = new ArrayList<>();
    @Column(name = "orderPrice", nullable = false)
    private BigDecimal orderPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<Product> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Order() {
    }

    public Order(Long id,List<Product> orderProducts, BigDecimal orderPrice) {
        this.id = id;
        this.orderProducts = orderProducts;
        this.orderPrice = orderPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderProducts=" + orderProducts +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
