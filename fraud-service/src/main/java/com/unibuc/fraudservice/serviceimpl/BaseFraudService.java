package com.unibuc.fraudservice.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.unibuc.fraudservice.model.GateLocation;
import com.unibuc.fraudservice.proxy.CartProxy;
import com.unibuc.fraudservice.service.FraudService;
import com.unibuc.identityservice.entity.Shopper;
import com.unibuc.identityservice.entity.Product;
import com.unibuc.identityservice.entity.Promotion;

@Service
public class BaseFraudService implements FraudService {

	private final CartProxy cartProxy;

	@Autowired
	public BaseFraudService(CartProxy cartProxy) {
		this.cartProxy = cartProxy;
	}

	@Override
	public String getShopperMessage(String shopperFirstName, Boolean isShopperBirthday, Boolean isShopperFirstSessionToday, GateLocation gateLocation) {

		StringBuilder message = new StringBuilder();
		switch (gateLocation) {
			case ENTRY:
				if (isShopperBirthday && isShopperFirstSessionToday) {
					/*
					 * If shopper's birthday is today, and they are on the first shopping trip at the store today, display message on 5% birthday discount.
					 */
					message.append("Happy Birthday, ").append(shopperFirstName).append("!").append(" Enjoy 5% discount on your shopping cart on your first trip today!");
				} else {
					/*
					 * Display welcome message to customer with no saved products about the option of saving favourite products.
					 */
					message.append(String.format("Welcome, %s! Don't forget to save your favourite products on your trip!", shopperFirstName));
				}
				break;
			case EXIT:
				if (isShopperBirthday && isShopperFirstSessionToday) {
					message.append(new StringBuilder().append("Enjoy your birthday today, ").append(shopperFirstName).append("!").append(" You have just received 5% discount on your shopping!"));
				} else {
					message.append("We hope you had a good session, ").append(shopperFirstName).append("! ").append("We are waiting for you to come back soon!");
				}
				break;
		}
		return message.toString();
	}

	@Override
	public String getMissingItemsMessage(Shopper shopper, List<Long> cartProductsIds) {
		StringBuilder missingItemsMessage = new StringBuilder("You forgot to take:\n");
		List<Product> customerShoppingList = shopper.getShoppingList();
		List<Product> missingProducts = customerShoppingList.stream().filter(p -> !cartProductsIds.contains(p.getId())).collect(Collectors.toList());
		for (Product p: missingProducts) {
			missingItemsMessage.append(p.toString()).append("\n");
		}
		return missingItemsMessage.toString();
	}

	@Override
	public String getCurrentPromotionsMessage(List<Promotion> promotionList) {
		StringBuilder currentPromotionsMessage = new StringBuilder("Promotions for you today: ");
		for (Promotion p : promotionList) {
			currentPromotionsMessage.append(p.toString()).append("\n");
		}
		return promotionList.isEmpty() ? "No current promotions for your saved products today." : currentPromotionsMessage.toString();
	}
}
