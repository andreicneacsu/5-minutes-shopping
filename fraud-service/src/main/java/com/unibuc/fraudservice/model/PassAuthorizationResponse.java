package com.unibuc.fraudservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassAuthorizationResponse {

    private Boolean isShopperAuthorized;

    private String shopperMessage;

    private Double bonusPointsEarned;

    private String missingItemsMessage;

    private String currentPromotionsMessage;

    private Double cartValue;

    private String authenticationEventId;
}
