package com.unibuc.identityservice.service;

import java.util.List;

import com.unibuc.identityservice.entity.Product;

public interface ProductService {

	List<Product> getAll();
	Product getProduct(Long productId);
	Product addProduct(Product product);
}
