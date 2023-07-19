package com.unibuc.productservice.service;

import java.util.List;

import com.unibuc.productservice.entity.Product;

public interface ProductService {

	List<Product> getAll();
	Product getProduct(Long productId);
	Product addProduct(Product product);
}
