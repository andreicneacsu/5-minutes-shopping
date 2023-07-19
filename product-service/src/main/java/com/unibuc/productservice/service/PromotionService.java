package com.unibuc.productservice.service;


import java.util.List;

import com.unibuc.cartservice.entity.CartItem;
import com.unibuc.productservice.entity.BonusPromotion;
import com.unibuc.productservice.entity.DiscountPromotion;
import com.unibuc.productservice.entity.Product;
import com.unibuc.productservice.entity.Promotion;
import com.unibuc.productservice.model.PromotionType;

public interface PromotionService {

	Double applyBonusPromotions(List<CartItem> cartItemsList);
	Double applyDiscountPromotions(List<CartItem> cartItemsList);
	BonusPromotion addBonusPromotion(BonusPromotion bonusPromotion);
	DiscountPromotion addDiscountPromotion(DiscountPromotion discountPromotion);
	Boolean isPromotionExpired(Long promotionId, PromotionType promotionType);
	BonusPromotion getActiveBonusPromotionForProduct(Long productId);
	DiscountPromotion getActiveDiscountPromotionForProduct(Long productId);
	List<Promotion> getPromotionsForProducts(List<Long> productIds);


}
