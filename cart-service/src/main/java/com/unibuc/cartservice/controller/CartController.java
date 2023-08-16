package com.unibuc.cartservice.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("")
	public Cart createCart(@RequestParam(value = "customerId") Long customerId, @RequestParam(value = "sessionId") String sessionId){
		return cartService.createCart(customerId, sessionId);
	}

	@GetMapping("/{id}")
	public Cart getCart(@PathVariable Long id){
		return cartService.getCartById(id);
	}

	@GetMapping("/{customerId}/search")
	public List<Cart> getCartsForCustomerOnDate(@PathVariable Long customerId, @RequestParam String currentDate) throws ParseException {
		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = sdf.parse(currentDate);
		return cartService.getCartForCustomerOnDate(customerId, date);
	}

	@GetMapping("/search")
	public Cart getCartForSessionId(@RequestParam String sessionId){
		return cartService.getCartForSessionId(sessionId);
	}
}
