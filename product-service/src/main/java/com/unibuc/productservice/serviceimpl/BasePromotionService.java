package com.unibuc.productservice.serviceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unibuc.cartservice.entity.Cart;
import com.unibuc.cartservice.entity.CartItem;
import com.unibuc.productservice.entity.BonusPromotion;
import com.unibuc.productservice.entity.DiscountPromotion;
import com.unibuc.productservice.entity.Product;
import com.unibuc.productservice.entity.Promotion;
import com.unibuc.productservice.exception.InvalidPromotionTypeException;
import com.unibuc.productservice.exception.ProductNotFoundException;
import com.unibuc.productservice.exception.PromotionNotFoundException;
import com.unibuc.productservice.model.PromotionType;
import com.unibuc.productservice.repository.BonusPromotionRepository;
import com.unibuc.productservice.repository.DiscountPromotionRepository;
import com.unibuc.productservice.repository.ProductRepository;
import com.unibuc.productservice.service.PromotionService;

@Service
public class BasePromotionService implements PromotionService {

	private BonusPromotionRepository bonusPromotionRepository;

	private DiscountPromotionRepository discountPromotionRepository;

	private ProductRepository productRepository;

	@Autowired
	public BasePromotionService(BonusPromotionRepository bonusPromotionRepository, DiscountPromotionRepository discountPromotionRepository, ProductRepository productRepository) {
		this.bonusPromotionRepository = bonusPromotionRepository;
		this.discountPromotionRepository = discountPromotionRepository;
		this.productRepository = productRepository;
	}

	@Override
	public Double applyBonusPromotions(List<CartItem> cartItemsList) {
		Double totalBonusPointsEarned = 0.0;
		for (CartItem i: cartItemsList) {
			BonusPromotion bonusPromotion = getActiveBonusPromotionForProduct(i.getProductId());
			if (bonusPromotion != null) {
				if (i.getQuantity().compareTo(bonusPromotion.getMinQuantity()) >= 0) {
					totalBonusPointsEarned += bonusPromotion.getBonusPointsEarned();
				}
			}
		}
		return totalBonusPointsEarned;
	}

	@Override
	public Double applyDiscountPromotions(List<CartItem> cartItemsList) {
		Double totalPriceAfterDiscount = 0.0;
		for (CartItem i: cartItemsList) {
			Optional<Product> p = productRepository.findById(i.getProductId());
			if (!p.isPresent()) {
				throw new ProductNotFoundException("Product with id: " + i.getProductId() + " not found.");
			}
			DiscountPromotion discountPromotion = getActiveDiscountPromotionForProduct(p.get().getId());
			if (discountPromotion != null) {
				if (i.getQuantity().compareTo(discountPromotion.getMinQuantity()) >= 0) {
					totalPriceAfterDiscount +=  i.getQuantity() * (p.get().getPricePerMeasureUnit() * (100 - discountPromotion.getDiscountPercent()) / 100);
				}
			} else {
				totalPriceAfterDiscount += i.getQuantity() * p.get().getPricePerMeasureUnit();
			}
		}
		return totalPriceAfterDiscount;
	}

	@Override
	public BonusPromotion addBonusPromotion(BonusPromotion bonusPromotion) {
		return bonusPromotionRepository.save(bonusPromotion);
	}

	@Override
	public DiscountPromotion addDiscountPromotion(DiscountPromotion discountPromotion) {
		return discountPromotionRepository.save(discountPromotion);
	}

	@Override
	public Boolean isPromotionExpired(Long promotionId, PromotionType promotionType) {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String currentDate = dateFormat.format(Calendar.getInstance());
		String expiryDate;

		switch (promotionType) {
			case BONUS:

				Optional<BonusPromotion> bonusPromotion = bonusPromotionRepository.findById(promotionId);
				if (bonusPromotion.isPresent()) {
					expiryDate = dateFormat.format(bonusPromotion.get().getExpiresOn());
					return currentDate.compareTo(expiryDate) >= 0;
				}
				throw new PromotionNotFoundException("Bonus promotion with id: " + promotionId + " not found.");
			case DISCOUNT:

				Optional<DiscountPromotion> discountPromotion = discountPromotionRepository.findById(promotionId);
				if (discountPromotion.isPresent()) {
					expiryDate = dateFormat.format(discountPromotion.get().getExpiresOn());
					return currentDate.compareTo(expiryDate) >= 0;
				}
				throw new PromotionNotFoundException("Discount promotion with id: " + promotionId + " not found.");
			default:

				throw new InvalidPromotionTypeException();
		}
	}

	@Override
	public BonusPromotion getActiveBonusPromotionForProduct(Long productId) {
		Optional<BonusPromotion> bonusPromotion = bonusPromotionRepository.findActivePromotionForProduct(productId);
		return bonusPromotion.orElse(null);
	}

	@Override
	public DiscountPromotion getActiveDiscountPromotionForProduct(Long productId) {
		Optional<DiscountPromotion> discountPromotion = discountPromotionRepository.findActivePromotionForProduct(productId);
		return discountPromotion.orElse(null);
	}

	@Override
	public List<Promotion> getPromotionsForProducts(List<Long> productIds) {
		List<Promotion> promotions = new ArrayList<>();
		for (Long productId: productIds) {
			Optional<DiscountPromotion> discountPromotion = discountPromotionRepository.findActivePromotionForProduct(productId);
			discountPromotion.ifPresent(promotions::add);
			Optional<BonusPromotion> bonusPromotion = bonusPromotionRepository.findActivePromotionForProduct(productId);
			bonusPromotion.ifPresent(promotions::add);
		}
		return promotions;
	}
}
