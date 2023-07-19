package com.unibuc.fraudservice.builder;

import com.unibuc.fraudservice.model.PassAuthorizationResponse;

public class PassAuthorizationResponseBuilder implements PassAuthorizationBuilder {

	private PassAuthorizationResponse passAuthorizationResponse;

	public PassAuthorizationResponseBuilder() {
		this.passAuthorizationResponse = new PassAuthorizationResponse();
	}

	@Override
	public PassAuthorizationBuilder withIsShopperAuthorized(Boolean isShopperAuthorized) {
		passAuthorizationResponse.setIsShopperAuthorized(isShopperAuthorized);
		return this;
	}

	@Override
	public PassAuthorizationBuilder withShopperMessage(String shopperMessage) {
		passAuthorizationResponse.setShopperMessage(shopperMessage);
		return this;
	}

	@Override
	public PassAuthorizationBuilder withBonusPointsEarned(Double bonusPointsEarned) {
		passAuthorizationResponse.setBonusPointsEarned(bonusPointsEarned);
		return this;
	}

	@Override
	public PassAuthorizationBuilder withMissingItemsMessage(String missingItemsMessage) {
		passAuthorizationResponse.setMissingItemsMessage(missingItemsMessage);
		return this;
	}

	@Override
	public PassAuthorizationBuilder withCartValue(Double cartValue) {
		passAuthorizationResponse.setCartValue(cartValue);
		return this;
	}

	@Override
	public PassAuthorizationBuilder withAuthenticationEventId(String authenticationEventId) {
		passAuthorizationResponse.setAuthenticationEventId(authenticationEventId);
		return this;
	}

	@Override
	public PassAuthorizationBuilder withCurrentPromotionsMessage(String currentPromotionsMessage) {
		passAuthorizationResponse.setCurrentPromotionsMessage(currentPromotionsMessage);
		return this;
	}

	@Override
	public PassAuthorizationResponse build() {
		return passAuthorizationResponse;
	}
}
