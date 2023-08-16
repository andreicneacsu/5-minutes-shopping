package com.unibuc.cartservice.serviceimpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unibuc.cartservice.entity.Cart;
import com.unibuc.cartservice.entity.CartItem;
import com.unibuc.cartservice.exception.CartNotFoundException;
import com.unibuc.cartservice.repository.CartRepository;
import com.unibuc.cartservice.service.CartService;

@Service
public class BaseCartService implements CartService {

	private CartRepository cartRepository;

	@Autowired
	public BaseCartService(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Override
	public List<Cart> getAll() {
		return cartRepository.findAll();
	}

	@Override
	public Cart getCartById(Long cartId) {
		Optional<Cart> cart = cartRepository.findById(cartId);
		if (cart.isPresent())
			return cart.get();
		throw new CartNotFoundException("Cart with id: " + cartId + " not found.");
	}

	@Override
	public Boolean addItemToCart(Long cartId, CartItem cartItem) {
		Optional<Cart> cart = cartRepository.findById(cartId);
		if (cart.isPresent()) {
			Cart c = cart.get();
			return c.getItems().add(cartItem);
		}
		throw new CartNotFoundException("Cart with id: " + cartId + " not found.");
	}

	@Override
	public Boolean removeItemFromCart(Long cartId, CartItem cartItem) {
		Optional<Cart> cart = cartRepository.findById(cartId);
		if (cart.isPresent()) {
			Cart c = cart.get();
			return c.getItems().remove(cartItem);
		}
		throw new CartNotFoundException("Cart with id: " + cartId + " not found.");

	}

	@Override
	public Cart createCart(Long shopperId, String sessionId) {
		Cart cart = new Cart();
		cart.setCustomerId(shopperId);
		cart.setSessionId(sessionId);
		cart.setDate(new Date());
		return cartRepository.save(cart);
	}

	@Override
	public Cart getCartForSessionId(String sessionId) {
		Optional<Cart> cart = cartRepository.findBySessionId(sessionId);
		if (cart.isPresent())
			return cart.get();
		throw new CartNotFoundException("Cart for session with id: " + sessionId + " not found.");
	}

	@Override
	public List<Cart> getCartForCustomerOnDate(Long customerId, Date date) {
		return cartRepository.findByCustomerIdAndDate(customerId, date);
	}
}
