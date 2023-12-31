package com.unibuc.cartservice.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.unibuc.cartservice.repository.CartItemRepository;
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

	private CartItemRepository cartItemRepository;

	@Autowired
	public BaseCartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {

		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
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
	public Cart addItemToCart(Long cartId, CartItem cartItem) {
		Optional<Cart> cart = cartRepository.findById(cartId);
		if (cart.isPresent()) {
			Cart c = cart.get();
			cartItem.setCart(c);
			cartItemRepository.save(cartItem);
			c.getItems().add(cartItem);
			return cartRepository.save(c);
		}
		throw new CartNotFoundException("Cart with id: " + cartId + " not found.");
	}

	@Override
	public Cart removeItemFromCart(Long cartId, CartItem cartItem) {
		Optional<Cart> cart = cartRepository.findById(cartId);
		if (cart.isPresent()) {
			Cart c = cart.get();
			cartItem.setCart(null);
			cartItemRepository.delete(cartItem);
			c.getItems().remove(cartItem);
			return cartRepository.save(c);
		}
		throw new CartNotFoundException("Cart with id: " + cartId + " not found.");

	}

	@Override
	public Cart createCart(Long shopperId, String sessionId) {
		Optional<Cart> existingCart = cartRepository.findBySessionId(sessionId);
		if (!existingCart.isPresent()) {
			Cart cart = new Cart();
			cart.setCustomerId(shopperId);
			cart.setSessionId(sessionId);
			cart.setDate(new Date());
			return cartRepository.save(cart);
		}
		return existingCart.get();
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
