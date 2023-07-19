package com.unibuc.identityservice.repository;

import java.util.List;
import java.util.Optional;

import com.unibuc.identityservice.entity.Shopper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, Long> {

	@Query("SELECT s FROM Shopper s WHERE s.id = ?1")
	Optional<Shopper> findById(Long shopperId);

	@Query("SELECT s FROM Shopper s")
	List<Shopper> findAll();

}
