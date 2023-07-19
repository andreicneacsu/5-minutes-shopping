package com.unibuc.fraudservice.service;

import java.util.Date;
import java.util.List;

import com.unibuc.fraudservice.model.GateLocation;
import com.unibuc.identityservice.entity.Shopper;
import com.unibuc.productservice.entity.Product;
import com.unibuc.productservice.entity.Promotion;

public interface FraudService {

	String getCurrentPromotionsMessage(List<Promotion> promotionList);
	String getShopperMessage(String shopperFirstName, Boolean isShopperBirthday, Boolean isShopperFirstSessionToday, GateLocation gateLocation);
	String getMissingItemsMessage(Shopper shopper, List<Long> cartProductsList);
}
