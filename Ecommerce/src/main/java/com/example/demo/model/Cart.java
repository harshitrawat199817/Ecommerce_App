package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    private final String id = UUID.randomUUID().toString(); // MongoDB uses String for IDs

    @Indexed(unique = true)
    private String userId; // Identifies the user

    private List<CartItem> cartItems = new ArrayList<>();

    private Double totalPrice;
    
    @Indexed(expireAfterSeconds = 60*60*24*7) // 7 day
    private Instant updatedAt;
    
    
}


