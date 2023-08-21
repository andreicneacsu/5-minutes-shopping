package com.unibuc.identityservice.serviceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import com.unibuc.cartservice.entity.CartItem;
import com.unibuc.identityservice.entity.Product;
import com.unibuc.identityservice.exception.ProductNotFoundException;
import com.unibuc.identityservice.repository.BonusPromotionRepository;
import com.unibuc.identityservice.repository.DiscountPromotionRepository;
import com.unibuc.identityservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unibuc.identityservice.entity.BonusPromotion;
import com.unibuc.identityservice.entity.DiscountPromotion;
import com.unibuc.identityservice.entity.Promotion;
import com.unibuc.identityservice.exception.InvalidPromotionTypeException;
import com.unibuc.identityservice.exception.PromotionNotFoundException;
import com.unibuc.identityservice.model.PromotionType;
import com.unibuc.identityservice.service.PromotionService;

@Service
public class BasePromotionService implements PromotionService {

	private BonusPromotionRepository bonusPromotionRepository;

	private DiscountPromotionRepository discountPromotionRepository;

	private ProductRepository productRepository;

	@Autowired
	public BasePromotionService(BonusPromotionRepository bonusPromotionRepository, DiscountPromotionRepository discountPromotionRepository,
								ProductRepository productRepository) {
		this.bonusPromotionRepository = bonusPromotionRepository;
		this.discountPromotionRepository = discountPromotionRepository;
		this.productRepository = productRepository;
	}

	@Override
	public BonusPromotion addBonusPromotion(BonusPromotion bonusPromotion) {
		return bonusPromotionRepository.save(bonusPromotion);
	}

	@Override
	public List<Promotion> getAllPromotions() {
		List<Promotion> promotionsList = new ArrayList<>();
		promotionsList.addAll(bonusPromotionRepository.findAll());
		promotionsList.addAll(discountPromotionRepository.findAll());
		return promotionsList;
	}

	@Override
	public DiscountPromotion addDiscountPromotion(DiscountPromotion discountPromotion) {
		return discountPromotionRepository.save(discountPromotion);
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
	public DiscountPromotion getActiveDiscountPromotionForProduct(Long productId) {
		Optional<DiscountPromotion> discountPromotion = discountPromotionRepository.findActivePromotionForProduct(productId);
		return discountPromotion.orElse(null);
	}

	@Override
	public BonusPromotion getActiveBonusPromotionForProduct(Long productId) {
		Optional<BonusPromotion> bonusPromotion = bonusPromotionRepository.findActivePromotionForProduct(productId);
		return bonusPromotion.orElse(null);
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
