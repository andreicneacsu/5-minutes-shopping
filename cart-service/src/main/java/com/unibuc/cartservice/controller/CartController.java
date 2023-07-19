package com.unibuc.cartservice.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unibuc.cartservice.entity.Cart;
import com.unibuc.cartservice.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {
	
	private CartService cartService;

	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping("/")
	public List<Cart> getAllCarts(){
		return cartService.getAll();
	}

	@GetMapping("/{id}")
	public Cart getCart(@PathVariable Long id){
		return cartService.getCartById(id);
	}

	@GetMapping("/{customerId}:search")
	public List<Cart> getCartsForCustomerOnDate(@PathVariable Long customerId, @RequestParam Date currentDate){
		return cartService.getCartForCustomerOnDate(customerId, currentDate);
	}

	@GetMapping(":search")
	public Cart getCartForSessionId(@RequestParam String sessionId){
		return cartService.getCartForSessionId(sessionId);
	}
}
