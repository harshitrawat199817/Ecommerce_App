package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class OrderItem {

    @Id
    private final String id = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    
    private String productId;

    private int quantity;
    private Double price;

    public OrderItem(Order order, String productId, int quantity, Double price) {
        this.order = order;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
}

