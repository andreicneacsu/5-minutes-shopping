package com.unibuc.fraudservice.proxy;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unibuc.cartservice.entity.Cart;

@FeignClient(name="cart-service")
@RibbonClient(name="cart-service")
public interface CartProxy {

	@GetMapping("/carts/{customerId}:search")
	List<Cart> retrieveCartsForCustomerOnDate(@RequestParam(value = "customerId") Long customerId, @RequestParam(value = "currentDate") Date currentDate);

	@GetMapping("/carts:search")
	Cart retrieveCartForCustomerForSession(@RequestParam(value = "sessionId") String sessionId);

	@PostMapping("/carts/")
	Cart createCart(@RequestParam(value = "customerId") Long customerId, @RequestParam(value = "sessionId") String sessionId);

}
