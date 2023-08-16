package com.unibuc.identityservice.controller;

import java.util.List;

import com.unibuc.identityservice.entity.Promotion;
import com.unibuc.identityservice.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unibuc.cartservice.entity.CartItem;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

	private PromotionService promotionService;

	@Autowired
	public PromotionController(PromotionService promotionService) {
		this.promotionService = promotionService;
	}

	@PostMapping("/search")
	List<Promotion> retrievePromotionsForProducts(@RequestBody List<Long> productIds) {

		return promotionService.getPromotionsForProducts(productIds);
	}

	@PostMapping("/bonus:apply")
	public Double applyBonusPromotions(@RequestBody List<CartItem> cartItemsList) {
		return promotionService.applyBonusPromotions(cartItemsList);
	}

	@PostMapping("/discount:apply")
	public Double applyDiscountPromotions(@RequestBody List<CartItem> cartItemsList) {
		return promotionService.applyDiscountPromotions(cartItemsList);
	}
}
