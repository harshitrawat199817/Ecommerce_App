package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

	@Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestParam String userId, 
                                          @RequestParam String productId, 
                                          @RequestParam int quantity,
                                          @RequestParam Double price) {
        return ResponseEntity.ok(cartService.addToCart(userId, productId, quantity, price));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Cart> removeFromCart(@RequestParam String userId, 
                                               @RequestParam String productId) {
        return ResponseEntity.ok(cartService.removeFromCart(userId, productId));
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/items/{userId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }
}

