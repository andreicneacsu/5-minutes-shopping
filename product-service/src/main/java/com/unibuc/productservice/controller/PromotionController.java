package com.unibuc.productservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unibuc.cartservice.entity.CartItem;
import com.unibuc.productservice.entity.Product;
import com.unibuc.productservice.entity.Promotion;
import com.unibuc.productservice.service.ProductService;
import com.unibuc.productservice.service.PromotionService;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

	private PromotionService promotionService;

	@Autowired
	public PromotionController(PromotionService promotionService) {
		this.promotionService = promotionService;
	}

	@PostMapping("/bonus:apply")
	public Double applyBonusPromotions(@RequestBody List<CartItem> cartItemsList) {
		return promotionService.applyBonusPromotions(cartItemsList);
	}

	@PostMapping("/discount:apply")
	public Double applyDiscountPromotions(@RequestBody List<CartItem> cartItemsList) {
		return promotionService.applyDiscountPromotions(cartItemsList);
	}

	@GetMapping("/promotions:search")
	List<Promotion> retrievePromotionsForProducts(@RequestBody List<Long> productIds) {

		return promotionService.getPromotionsForProducts(productIds);
	}
}
