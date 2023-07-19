package com.unibuc.fraudservice.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.unibuc.cartservice.entity.CartItem;
import com.unibuc.productservice.entity.Promotion;

@FeignClient(name="product-service")
@RibbonClient(name="product-service")
public interface ProductProxy {

	@GetMapping("/promotions")
	List<Promotion> retrieveAllPromotions();

	@GetMapping("/promotions:search")
	List<Promotion> retrievePromotionsForProducts(@RequestBody List<Long> productIds);

	@PostMapping("/promotions/bonus:apply")
	Double applyBonusPromotions(@RequestBody List<CartItem> cartItemsList);

	@PostMapping("/promotions/discount:apply")
	Double applyDiscountPromotions(@RequestBody List<CartItem> cartItemsList);
}
