package com.unibuc.identityservice.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.unibuc.identityservice.entity.Shopper;
import com.unibuc.identityservice.service.ShopperService;
import com.unibuc.identityservice.entity.Product;

@RestController
@RequestMapping("/shoppers")
public class ShopperController {

	private ShopperService shopperService;

	@Autowired
	public ShopperController(ShopperService shopperService) {
		this.shopperService = shopperService;
	}

	@GetMapping("/")
	public List<Shopper> getAllShoppers(){
		return shopperService.getAll();
	}

	@GetMapping("/{id}")
	public Shopper getShopper(@PathVariable Long id){
		return shopperService.getShopper(id);
	}

	@GetMapping("/favourites")
	public List<Product> getFavouriteProductsForShopper(@RequestParam Long shopperId){
		return shopperService.getFavouriteProductsForShopper(shopperId);
	}

	@GetMapping("/{shopperId}/birthday")
	public Boolean isShopperBirthday(@PathVariable Long shopperId){
		return shopperService.isShopperBirthday(shopperId, Calendar.getInstance().getTime());
	}

	@PutMapping("/{shopperId}")
	public Shopper updateShopper(@PathVariable Long shopperId, @RequestBody Shopper updatedShopper){
		return shopperService.updateShopper(shopperId, updatedShopper);
	}
}
