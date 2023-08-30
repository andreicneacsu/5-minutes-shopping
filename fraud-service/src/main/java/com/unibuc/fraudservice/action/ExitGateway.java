package com.unibuc.fraudservice.action;

import com.unibuc.cartservice.entity.Cart;
import com.unibuc.cartservice.entity.CartItem;
import com.unibuc.fraudservice.builder.PassAuthorizationResponseBuilder;
import com.unibuc.fraudservice.exception.DependencyFailure;
import com.unibuc.fraudservice.exception.InactiveStoreException;
import com.unibuc.fraudservice.exception.InvalidAuthenticationLocation;
import com.unibuc.fraudservice.model.GateLocation;
import com.unibuc.fraudservice.model.PassAuthorizationRequest;
import com.unibuc.fraudservice.model.PassAuthorizationResponse;
import com.unibuc.fraudservice.proxy.CartProxy;
import com.unibuc.fraudservice.proxy.ShopperProxy;
import com.unibuc.fraudservice.proxy.StoreProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unibuc.fraudservice.service.FraudService;
import com.unibuc.identityservice.entity.Shopper;
import com.unibuc.storeservice.exception.StoreNotFoundException;
import com.unibuc.storeservice.entity.Store;
import com.unibuc.storeservice.model.City;

import static com.unibuc.fraudservice.util.Constants.STORE_REGISTRY_FAILURE;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Component
public class ExitGateway implements GatewayAction {

    private static final Logger log = LogManager.getLogger(ExitGateway.class);

    private StoreProxy storeProxy;

    private ShopperProxy shopperProxy;

    private CartProxy cartProxy;

    private FraudService fraudService;

    @Autowired
    public ExitGateway(StoreProxy storeProxy, ShopperProxy shopperProxy, CartProxy cartProxy, FraudService fraudService) {
        this.storeProxy = storeProxy;
        this.shopperProxy = shopperProxy;
        this.cartProxy = cartProxy;
        this.fraudService = fraudService;
    }

    @Override
    public PassAuthorizationResponse passAuthorization(PassAuthorizationRequest request) {

        /*
         * Check if store with storeId exists in store registry.
         */
        Store store;
        try {
            store = storeProxy.retrieveStore(request.getStoreId());
            log.info("Successfully retrieved store: " + store + "from store registry.");
        } catch (StoreNotFoundException e) {
            log.error(String.format("Store with id: %s not found!", request.getStoreId()));
            throw e;
        } catch (Exception e) {
            log.error(String.format("Error when retrieving store from store registry: %s", e));
            throw new DependencyFailure(STORE_REGISTRY_FAILURE);
        }

        /*
         * Check if store is active.
         */
        log.info("Checking if store with id: " + store.getId() +  " is active...");
        Boolean isStoreActive = store != null ? store.getActiveStatus() : false;
        if (!isStoreActive) {

            log.warn(String.format("Store with id: %s is INACTIVE at: %s. Pass authorization denied.", request.getStoreId(), request.getAuthenticationEvent().getTimestamp()));
            throw new InactiveStoreException();
        }

        /*
         * Retrieve the store tag to verify if store allows EXIT authentication.
         */
        Boolean isStoreWithExitAuthorization = store.getStoreTag() == City.StoreTag.EXIT_PASS || store.getStoreTag() == City.StoreTag.ENTRY_EXIT_PASS;
        if (!isStoreWithExitAuthorization) {

            log.warn(String.format("Store with id: %s does not allow EXIT_PASS. Pass authorization denied.", request.getStoreId()));
            throw new InvalidAuthenticationLocation(GateLocation.EXIT.name());
        }

        /*
         * Decrease current capacity for store.
         */
        log.info("Decrementing current store capacity for store with id: " + store.getId());
        store.setCurrentCapacity(store.getCurrentCapacity() + 1);
        storeProxy.updateStore(store.getId(), store);

        /*
         * Retrieve customer cart and show total price
         */
        Cart customerCart = cartProxy.retrieveCartForCustomerForSession(request.getAuthenticationEvent().getId());
        log.info("Retrieved customer cart: " + customerCart + "for session with id: " + request.getAuthenticationEvent().getId());
        Double cartValue = shopperProxy.applyDiscountPromotions(customerCart.getItems());

        /*
         * Retrieve customer shopping list and display cart missing items present on the shopping list.
         */
        Shopper shopper = shopperProxy.retrieveShopper(request.getShopperId());
        log.info("Retrieved current shopper: " + shopper);

        String missingItemsMessage = fraudService.getMissingItemsMessage(shopper, customerCart.getItems().stream().map(CartItem::getProductId).collect(Collectors.toList()));

        /*
         * If a number of bonus points reached OR it is shopper's birthday and their first shopping session today, give him 5% discount on his shopping cart, apply discount
         */
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String timestamp = sdf.format(request.getAuthenticationEvent().getTimestamp());
        Boolean isShopperBirthday = shopperProxy.isShopperBirthday(shopper.getId());
        Boolean isShopperFirstSession = cartProxy.retrieveCartsForCustomerOnDate(shopper.getId(), sdf.format(Calendar.getInstance().getTime())).isEmpty();

        if (shopper.getBonusPoints() >= store.getBonusPointsDiscount().doubleValue()) {
            log.info("Applying 5% discount for 100 bonus points reached on initial cart value: " + cartValue);
            cartValue  = 0.95 * cartValue;
            /*
             * Use 100 bonus points from shopper's points earned.
             */
            shopper.setBonusPoints(shopper.getBonusPoints() - 100);
        } else if (isShopperBirthday && isShopperFirstSession) {
            log.info("Applying 5% discount on shopper's birthday on initial cart value: " + cartValue);
            cartValue = 0.95 * cartValue;
        }

        /*
         * Compute bonus points earned by customer on shopping trip.
         */
        Double bonusPointsEarned = shopperProxy.applyBonusPromotions(customerCart.getItems());
        log.info("Bonus points earned by shopper: " + bonusPointsEarned);

        /*
        Get shopper farewell gate message.
         */
        String shopperMessage = fraudService.getShopperMessage(shopper.getFirstName(), isShopperBirthday, isShopperFirstSession, GateLocation.EXIT);
        return new PassAuthorizationResponseBuilder()
                .withIsShopperAuthorized(true)
                .withShopperMessage(shopperMessage)
                .withBonusPointsEarned(bonusPointsEarned)
                .withAuthenticationEventId(request.getAuthenticationEvent().getId())
                .withCartValue(cartValue)
                .withMissingItemsMessage(missingItemsMessage)
                .build();

    }
}
