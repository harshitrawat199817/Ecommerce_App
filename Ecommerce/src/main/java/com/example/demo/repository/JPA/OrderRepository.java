package com.example.demo.repository.JPA;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByUserId(String userId);

	Optional<Order> findById(String orderId);

}
