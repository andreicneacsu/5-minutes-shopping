package com.unibuc.cartservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.unibuc.cartservice.entity.Cart;
import com.unibuc.cartservice.entity.CartItem;

public interface CartService {


	List<Cart> getAll();
	Cart getCartById(Long cartId);
	Boolean addItemToCart(Long cartId, CartItem item);
	Boolean removeItemFromCart(Long cartId, CartItem item);
	Cart createCart(Long customerId, String sessionId);
	Cart getCartForSessionId(String sessionId);
	List<Cart> getCartForCustomerOnDate(Long customerId, Date date);

}
