package com.unibuc.productservice.repository;

import java.util.Optional;

import com.unibuc.productservice.entity.BonusPromotion;
import com.unibuc.productservice.entity.DiscountPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountPromotionRepository extends JpaRepository<DiscountPromotion, Long> {

	@Query("SELECT dp FROM DiscountPromotion dp WHERE dp.product.id = ?1 AND dp.expiresOn > CURRENT_DATE")
	Optional<DiscountPromotion> findActivePromotionForProduct(Long productId);
}
