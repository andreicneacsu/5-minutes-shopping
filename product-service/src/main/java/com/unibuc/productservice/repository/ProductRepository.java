package com.unibuc.productservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unibuc.productservice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p WHERE p.id = ?1")
	Optional<Product> findById(Long productId);

	@Query("SELECT p FROM Product p")
	List<Product> findAll();

	@Query(value = "SELECT * FROM product_favourites pf WHERE pf.shopper_id =?1", nativeQuery = true)
	List<Product> findFavouritesByShopperId(Long shopperId);
}
