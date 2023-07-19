package com.unibuc.fraudservice.builder;

import com.unibuc.fraudservice.model.PassAuthorizationResponse;

public interface PassAuthorizationBuilder {

	PassAuthorizationBuilder withIsShopperAuthorized(Boolean isShopperAuthorized);
	PassAuthorizationBuilder withShopperMessage(String shopperMessage);
	PassAuthorizationBuilder withBonusPointsEarned(Double bonusPointsEarned);
	PassAuthorizationBuilder withMissingItemsMessage(String missingItemsMessage);
	PassAuthorizationBuilder withCartValue(Double cartValue);
	PassAuthorizationBuilder withAuthenticationEventId(String authenticationEventId);
	PassAuthorizationBuilder withCurrentPromotionsMessage(String currentPromotionsMessage);
	PassAuthorizationResponse build();

}
