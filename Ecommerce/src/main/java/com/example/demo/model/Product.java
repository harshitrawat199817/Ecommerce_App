package com.example.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    private final String id = UUID.randomUUID().toString();
    private String name;
    private String category;  // E.g., "Clothing", "Electronics"
    private String brand;
    private String description;
    private double basePrice;

    private List<ProductVariant> variants;  // Flexible structure for variants

    private Instant createdAt;
    private Instant updatedAt;
}


