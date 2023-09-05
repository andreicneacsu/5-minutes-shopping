package com.unibuc.identityservice.serviceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unibuc.identityservice.entity.Shopper;
import com.unibuc.identityservice.exception.ShopperNotFoundException;
import com.unibuc.identityservice.repository.ShopperRepository;
import com.unibuc.identityservice.service.ShopperService;
import com.unibuc.identityservice.entity.Product;
import com.unibuc.identityservice.repository.ProductRepository;

@Service
public class BaseShopperService implements ShopperService {

	private ShopperRepository shopperRepository;

	private ProductRepository productRepository;

	@Autowired
	public BaseShopperService(ShopperRepository shopperRepository, ProductRepository productRepository) {
		this.shopperRepository = shopperRepository;
		this.productRepository = productRepository;
	}

	@Override
	public List<Shopper> getAll() {
		return shopperRepository.findAll();
	}

	@Override
	public Shopper getShopper(Long shopperId) {

		Optional<Shopper> shopper = shopperRepository.findById(shopperId);

		if (shopper.isPresent())
			return shopper.get();
		throw new ShopperNotFoundException("Shopper with id: " + shopperId + " not found.");
	}

	@Override
	public Shopper addShopper(Shopper shopper) {
		return shopperRepository.save(shopper);
	}

	@Override
	public List<Product> getFavouriteProductsForShopper(Long shopperId) {

		Optional<Shopper> shopper = shopperRepository.findById(shopperId);
		if (shopper.isPresent())
			return shopper.get().getFavouriteProducts();
		throw new ShopperNotFoundException("Shopper with id: " + shopperId + " not found.");
	}

	@Override
	public Boolean removeFavouriteProductForShopper(Long shopperId, Long productId) {
		Optional<Shopper> shopper = shopperRepository.findById(shopperId);
		Optional<Product> product = productRepository.findById(productId);
		if (shopper.isPresent() && product.isPresent()) {
			shopper.get().getFavouriteProducts().remove(product.get());
			return true;
		}
		return false;
	}

	@Override
	public Boolean addFavouriteProductForShopper(Long shopperId, Long productId) {
		Optional<Shopper> shopper = shopperRepository.findById(shopperId);
		Optional<Product> product = productRepository.findById(productId);
		if (shopper.isPresent() && product.isPresent()) {
			shopper.get().getFavouriteProducts().add(product.get());
			return true;
		}
		return false;
	}

	@Override
	public Boolean isShopperBirthday(Long shopperId, Date currentDate) {
		Optional<Shopper> shopper = shopperRepository.findById(shopperId);
		if (shopper.isPresent()) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM");
			Date shopperBirthday = shopper.get().getBirthDate();
			String shopperBirthdayString = dateFormat.format(shopperBirthday);
			String currentDateString = dateFormat.format(currentDate);
			return shopperBirthdayString.equals(currentDateString);
		}
		throw new ShopperNotFoundException(String.format("Shopper with id: %d not found.", shopperId));
	}

	@Override
	public Double addBonusPointsForShopper(Long shopperId, Long extraPoints) {
		Optional<Shopper> shopper = shopperRepository.findById(shopperId);
		if (shopper.isPresent())
			shopper.get().setBonusPoints(shopper.get().getBonusPoints() + extraPoints);

		throw new ShopperNotFoundException(String.format("Shopper with id: %d not found.", shopperId));
	}

	@Override
	public Shopper updateShopper(Long shopperId, Shopper updatedShopper) {
		Optional<Shopper> shopper = shopperRepository.findById(shopperId);
		if (shopper.isPresent())
		{
			Shopper oldShopper = shopper.get();
			oldShopper.setLastName(updatedShopper.getLastName());
			oldShopper.setBonusPoints(updatedShopper.getBonusPoints());
			oldShopper.setBirthDate(updatedShopper.getBirthDate());
			oldShopper.setFavouriteProducts(updatedShopper.getFavouriteProducts());
			oldShopper.setFirstName(updatedShopper.getFirstName());
			oldShopper.setShoppingList(updatedShopper.getShoppingList());
			return shopperRepository.save(oldShopper);

		}
		throw new ShopperNotFoundException(String.format("Shopper with id: %d not found.", shopperId));
	}

	@Override
	public Double removeBonusPointsForShopper(Long shopperId, Long minusPoints) {
		Optional<Shopper> shopper = shopperRepository.findById(shopperId);
		if (shopper.isPresent())
			shopper.get().setBonusPoints(shopper.get().getBonusPoints() - minusPoints);
		throw new ShopperNotFoundException(String.format("Shopper with id: %d not found.", shopperId));
	}
}
