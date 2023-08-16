package com.unibuc.identityservice.serviceimpl;

import java.util.List;
import java.util.Optional;

import com.unibuc.identityservice.entity.Product;
import com.unibuc.identityservice.exception.ProductNotFoundException;
import com.unibuc.identityservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unibuc.identityservice.repository.ProductRepository;

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
