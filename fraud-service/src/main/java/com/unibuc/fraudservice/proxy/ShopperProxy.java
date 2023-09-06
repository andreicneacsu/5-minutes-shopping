package com.unibuc.fraudservice.proxy;

import java.util.List;

import com.unibuc.cartservice.entity.CartItem;
import com.unibuc.identityservice.entity.Promotion;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.unibuc.identityservice.entity.Shopper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="identity-service")
@RibbonClient(name="identity-service")
public interface ShopperProxy {

	@GetMapping("/shoppers")
	List<Shopper> retrieveAllShoppers();

	@GetMapping("/shoppers/{id}")
	Shopper retrieveShopper(@PathVariable(value = "id") Long id);

	@GetMapping("/shoppers/{shopperId}/birthday")
	Boolean isShopperBirthday(@PathVariable(value = "shopperId") Long shopperId);

	@GetMapping("/promotions")
	List<Promotion> retrieveAllPromotions();

	@PostMapping("/promotions/search")
	List<Promotion> retrievePromotionsForProducts(@RequestBody List<Long> productIds);

	@PostMapping("/promotions/bonus:apply")
	Double applyBonusPromotions(@RequestBody List<CartItem> cartItemsList);

	@PostMapping("/promotions/discount:apply")
	Double applyDiscountPromotions(@RequestBody List<CartItem> cartItemsList);
}
