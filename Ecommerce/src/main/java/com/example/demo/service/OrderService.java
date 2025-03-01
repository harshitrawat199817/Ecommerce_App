package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Cart;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.repository.JPA.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private CartService cartService;

    @Transactional
    public Order createOrder(String userId) {
        Cart cart = cartService.getCartByUserId(userId);
        if (cart == null || cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUserId(userId);
        List<OrderItem> orderItems = cart.getCartItems().stream().map(item -> {
	        OrderItem orderItem = new OrderItem();
	        orderItem.setProductId(item.getProductId());
	        orderItem.setQuantity(item.getQuantity());
	        orderItem.setPrice(item.getTotalPrice()* item.getQuantity());
	        return orderItem;
        }).collect(Collectors.toList());
        
        order.setOrderItems(orderItems);
        order.setTotalAmount(cart.getTotalPrice());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        
        Order savedOrder = orderRepository.save(order);
        cartService.clearCart(userId);
        return savedOrder;
    }

    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order updateOrderStatus(String orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return orderRepository.save(order);
    }
}

