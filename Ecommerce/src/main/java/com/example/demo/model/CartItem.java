package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
	
	
    private String productId; // The product ID

    private int quantity;

    private Double pricePerUnit;

    private Double totalPrice;
    
    
}


