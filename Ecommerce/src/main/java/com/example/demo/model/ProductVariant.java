package com.example.demo.model;

import lombok.NoArgsConstructor;
import lombok.*;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariant {
	
    private final String variantId = UUID.randomUUID().toString();
    private int stock;
    private double price;
    private Map<String, Object> attributes;  // Stores category-specific attributes
}


