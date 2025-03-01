package com.example.demo.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.repository.MongoDB.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	
	/**
	 * Returns the user's cart.
	 */
	public Cart getCartByUserId(String userId) {
		return cartRepository.findCartByUserId(userId)
				.orElseGet(()-> {
					Cart cart = new Cart();
					cart.setUserId(userId);
					cart.setUpdatedAt(Instant.now());
					return cartRepository.save(cart);
					
				});
	}
	
	 /**
     * Adds a product to the user's cart.
     */
    public Cart addToCart(String userId, String productId, int quantity, Double price) {
        Cart cart = getCartByUserId(userId);

        // Check if product already exists in cart
        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            cart.getCartItems().add(new CartItem(productId, quantity, price, price*quantity));
        }

        cart.setUpdatedAt(Instant.now());
        return cartRepository.save(cart);
    }

    /**
     * Removes a product from the user's cart.
     */
    public Cart removeFromCart(String userId, String productId) {
        Cart cart = getCartByUserId(userId);
        cart.getCartItems().removeIf(item -> item.getProductId().equals(productId));
        cart.setUpdatedAt(Instant.now());
        return cartRepository.save(cart);
    }

    /**
     * Clears the entire cart for a user.
     */
    public void clearCart(String userId) {
        cartRepository.findCartByUserId(userId).ifPresent(cartRepository::delete);
    }

    /**
     * Retrieves all cart items for a given user.
     */
    public List<CartItem> getCartItems(String userId) {
        return getCartByUserId(userId).getCartItems();
    }
    
	/**
	 * Updates the quantity of a product in the user's cart.
	 */
	public Cart updateCartItemQuantity(String userId, String productId, int quantity) {
		Cart cart = getCartByUserId(userId);
		Optional<CartItem> existingItem = cart.getCartItems().stream()
				.filter(item -> item.getProductId().equals(productId))
				.findFirst();
		if (existingItem.isPresent()) {
			existingItem.get().setQuantity(quantity);
		}
		cart.setUpdatedAt(Instant.now());
		return cartRepository.save(cart);
	}
	
	
	
	
}
