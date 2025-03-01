package com.example.demo.repository.MongoDB;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {

	 @Query("{ 'category': ?0, 'price': { $gte: ?1, $lte: ?2 } }")
	 public Page<Product> findByCategoryAndPriceRange(
	        String category, 
	        double minPrice, 
	        double maxPrice, 
	        Pageable pageable
	    );
	 
	 public Optional<Product> findById(String id);
	 
	 public Optional<Product> findByProductId(String name);
	 
	 
	 @Query("{ 'name': ?0 }")
	public Page<Product> findByName(String name, Pageable pageable);
	 
	 @Query("{ 'variants.variantId': ?0, 'variants.stock': ?1 }")
	public void updateProductVariantQuantity(String productId, String variantId, int quantity);
	 
	 
}
