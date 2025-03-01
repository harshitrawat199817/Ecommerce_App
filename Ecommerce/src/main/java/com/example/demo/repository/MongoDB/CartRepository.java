package com.example.demo.repository.MongoDB;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, Long> {

	@Query("{'userId' : ?0}")
	Optional<Cart> findCartByUserId(String cartId);

}
