package com.unibuc.fraudservice.proxy;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unibuc.cartservice.entity.Cart;

@FeignClient(name="cart-service")
@RibbonClient(name="cart-service")
public interface CartProxy {

	@GetMapping("/carts/{customerId}:search")
	List<Cart> retrieveCartsForCustomerOnDate(@RequestParam Long customerId, @RequestParam Date currentDate);

	@GetMapping("/carts:search")
	Cart retrieveCartForCustomerForSession(@RequestParam String sessionId);

	@PostMapping("/carts/")
	Cart createCart(@RequestParam Long customerId, @RequestParam String sessionId);

}
