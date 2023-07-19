package com.unibuc.fraudservice.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.unibuc.identityservice.entity.Shopper;

@FeignClient(name="identity-service")
@RibbonClient(name="identity-service")
public interface ShopperProxy {

	@GetMapping("/shoppers")
	List<Shopper> retrieveAllShoppers();

	@GetMapping("/shoppers/{id}")
	Shopper retrieveShopper(@PathVariable Long id);

	@GetMapping("/shoppers/{shopperId}/birthday")
	Boolean isShopperBirthday(@PathVariable Long id);
}
