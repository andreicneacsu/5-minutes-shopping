package com.unibuc.identityservice.service;


import java.util.List;

import com.unibuc.cartservice.entity.CartItem;
import com.unibuc.identityservice.entity.BonusPromotion;
import com.unibuc.identityservice.entity.DiscountPromotion;
import com.unibuc.identityservice.entity.Promotion;
import com.unibuc.identityservice.model.PromotionType;

public interface PromotionService {

	BonusPromotion addBonusPromotion(BonusPromotion bonusPromotion);
	DiscountPromotion addDiscountPromotion(DiscountPromotion discountPromotion);
	Boolean isPromotionExpired(Long promotionId, PromotionType promotionType);
	List<Promotion> getPromotionsForProducts(List<Long> productIds);
	Double applyBonusPromotions(List<CartItem> cartItemsList);
	Double applyDiscountPromotions(List<CartItem> cartItemsList);
	BonusPromotion getActiveBonusPromotionForProduct(Long productId);
	DiscountPromotion getActiveDiscountPromotionForProduct(Long productId);


}
