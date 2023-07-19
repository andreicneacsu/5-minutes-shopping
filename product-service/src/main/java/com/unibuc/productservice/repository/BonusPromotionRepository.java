package com.unibuc.productservice.repository;

import java.util.Optional;

import com.unibuc.productservice.entity.BonusPromotion;
import com.unibuc.productservice.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusPromotionRepository extends JpaRepository<BonusPromotion, Long> {

	@Query("SELECT bp FROM BonusPromotion bp WHERE bp.product.id = ?1 AND bp.expiresOn > CURRENT_DATE")
	Optional<BonusPromotion> findActivePromotionForProduct(Long productId);
}
