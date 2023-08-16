package com.unibuc.identityservice.repository;

import com.unibuc.identityservice.entity.DiscountPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountPromotionRepository extends JpaRepository<DiscountPromotion, Long> {

    @Query("SELECT dp FROM DiscountPromotion dp WHERE dp.productId = ?1 AND dp.expiresOn > CURRENT_DATE")
    Optional<DiscountPromotion> findActivePromotionForProduct(Long productId);
}
