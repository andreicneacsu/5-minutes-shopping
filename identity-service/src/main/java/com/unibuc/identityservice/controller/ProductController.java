package com.unibuc.identityservice.controller;

import java.util.List;

import com.unibuc.identityservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.unibuc.identityservice.service.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {
	
	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/")
	public List<Product> getAllProducts(){
		return productService.getAll();
	}

	@GetMapping("/{id}")
	public Product getProduct(@PathVariable Long id) {
		return productService.getProduct(id);
	}

	@PostMapping("/")
	public Product addProduct(@RequestBody Product product) {

		return productService.addProduct(product);
	}
}
