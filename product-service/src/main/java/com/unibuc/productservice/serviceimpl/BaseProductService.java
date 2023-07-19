package com.unibuc.productservice.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unibuc.productservice.entity.Product;
import com.unibuc.productservice.exception.ProductNotFoundException;
import com.unibuc.productservice.repository.ProductRepository;
import com.unibuc.productservice.service.ProductService;

@Service
public class BaseProductService implements ProductService {

	private ProductRepository productRepository;

	@Autowired
	public BaseProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(Long productId) {
		Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent())
			return product.get();
		throw new ProductNotFoundException(String.format("Product with id: %d not found.", productId));
	}

	@Override
	public Product addProduct(Product product) {

		return productRepository.save(product);
	}
}
