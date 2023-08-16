package com.unibuc.identityservice.service;

import java.util.Date;
import java.util.List;

import com.unibuc.identityservice.entity.Shopper;
import com.unibuc.identityservice.entity.Product;

public interface ShopperService {

	List<Shopper> getAll();
	Shopper getShopper(Long shopperId);
	Shopper addShopper(Shopper shopper);
	Boolean isShopperBirthday(Long shopperId, Date currentDate);
	List<Product> getFavouriteProductsForShopper(Long shopperId);
	Boolean removeFavouriteProductForShopper(Long shopperId, Long productId);
	Boolean addFavouriteProductForShopper(Long shopperId, Long productId);
	Double removeBonusPointsForShopper(Long shopperId, Long minusPoints);
	Double addBonusPointsForShopper(Long shopperId, Long extraPoints);
}
