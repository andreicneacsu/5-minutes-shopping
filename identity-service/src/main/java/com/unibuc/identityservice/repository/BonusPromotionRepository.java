package com.unibuc.identityservice.repository;

import com.unibuc.identityservice.entity.BonusPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BonusPromotionRepository extends JpaRepository<BonusPromotion, Long> {

    @Query("SELECT bp FROM BonusPromotion bp WHERE bp.productId = ?1 AND bp.expiresOn > CURRENT_DATE")
    Optional<BonusPromotion> findActivePromotionForProduct(Long productId);
}