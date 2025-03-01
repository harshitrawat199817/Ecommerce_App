package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Order {

    @Id
    private String id = UUID.randomUUID().toString();

    
    private String userId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    private Double totalAmount;
    
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderDate;
  
    private String status;

    public Order(String userId, Double totalAmount, LocalDateTime orderDate, String status) {
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.status = status;
    }
}


