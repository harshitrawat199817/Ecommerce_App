package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.model.ProductVariant;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	/**
	 * Get all products
	 */
	@GetMapping
	public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") Integer page) {
		return productService.getAllProducts(page);
	}
	
	/**
	 * Get products based on filters
	 */
	@GetMapping("/filter")
	public Page<Product> getProductsByFilter(
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String brand,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) Double minPrice,
			@RequestParam(required = false) Double maxPrice,
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Boolean asc
			) {
		return productService.getFilteredProducts(category, minPrice, maxPrice, page, name, asc);
	}
	
	/**
	 * Get products based on name
	 */
	@GetMapping("/name")
	public Page<Product> getProductsByName(String name, Integer page) {
		return productService.getProductsByName(name, page);
	}
	
	/**
	 * Add a new product
	 * 	
	 */
	@PostMapping("/add")
	public Product addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}
	
	/**
	 * Add a new variant
	 * 	
	 */
	@PostMapping("/{id}/variant")
	public Product addVariant(@PathVariable String id, @RequestBody ProductVariant variant) {
		return productService.addVariant(id, variant);
	}
	
	/**
	 * Update a variant
	 * 
	 */
	@PostMapping("/{id}/update")
	public Product updateVariant(@PathVariable String id, @RequestBody ProductVariant variant) {
		return productService.updateVariant(id, variant);
	}
	
	/**
	 * Delete a variant
	 * 	
	 */
	@PostMapping("/{id}/delete")
	public Product deleteVariant(@PathVariable String id, @RequestBody ProductVariant variant) {
		return productService.deleteVariant(id, variant.getVariantId());
	}
	
	/**
	 * Get product by id
	 */
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable String id) {
		return productService.getProduct(id);
	}
	/**
	 * Delete product by id
	 */
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable String id) {
		productService.deleteProduct(id);
	}
	
	
}
