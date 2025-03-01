package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.model.ProductVariant;
import com.example.demo.repository.MongoDB.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository; // MongoDB

    /**
     * Fetch product by ID.
     */
    public Product getProduct(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
   
    /**
     * Add a new product with variants.
     */
    public Product addProduct(Product product) throws RuntimeException {
		if(product.getVariants() == null) {
			throw new RuntimeException("Variants cannot be null");
		}
        return productRepository.save(product);
    }
    

    /**
     * Add a new variant to an existing product.
     */
    public Product addVariant(String productId, ProductVariant newVariant) {
        Product product = getProduct(productId);
        product.getVariants().add(newVariant);
        return productRepository.save(product);
    }

    /**
     * Update a specific variant in a product.
     */
    public Product updateVariant(String productId, ProductVariant updatedVariant) {
        Product product = getProduct(productId);

        product.getVariants().forEach(variant -> {
            if (variant.getVariantId().equals(updatedVariant.getVariantId())) {
                variant.setPrice(updatedVariant.getPrice());
                variant.setAttributes(updatedVariant.getAttributes());
            }
        });

        return productRepository.save(product);
    }

    /**
     * Delete a specific variant from a product.
     */
    public Product deleteVariant(String productId, String variantId) {
        Product product = getProduct(productId);
        product.setVariants(
            product.getVariants().stream()
                    .filter(variant -> !variant.getVariantId().equals(variantId))
                    .toList()
        );
        return productRepository.save(product);
    }
    /**
	 * Get products based on filters
	 */
    public Page<Product> getFilteredProducts(String category, Double minPrice, Double maxPrice, Integer page, String sortBy, Boolean ascending) {
        Sort sort = ascending ? Sort.by(Sort.Direction.ASC, sortBy) : Sort.by(Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, 10, sort);
        return productRepository.findByCategoryAndPriceRange(category, minPrice, maxPrice, pageable);
    }
    
	/**
	 * Get products based on name
	 */
    public Page<Product> getProductsByName(String name, Integer page) {
	    Pageable pageable = PageRequest.of(0, 10);
	    return productRepository.findByName(name, pageable);
    }
    
	/**
	 * Get all products
	 */
    public Page<Product> getAllProducts(Integer page) {
    	
		Pageable pageable = PageRequest.of(page, 10);
		return productRepository.findAll(pageable);
	}


	/**
	 * Delete product by id
	 */
	public void deleteProduct(String id) {
		// TODO Auto-generated method stub
		Product product = getProduct(id);
		productRepository.delete(product);
		
	}
    
}

