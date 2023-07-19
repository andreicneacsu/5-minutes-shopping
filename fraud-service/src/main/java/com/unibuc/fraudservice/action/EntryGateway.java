package com.unibuc.fraudservice.action;

import static com.unibuc.fraudservice.util.Constants.STORE_REGISTRY_FAILURE;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.unibuc.cartservice.entity.Cart;
import com.unibuc.fraudservice.builder.PassAuthorizationResponseBuilder;
import com.unibuc.fraudservice.exception.DependencyFailure;
import com.unibuc.fraudservice.exception.InactiveStoreException;
import com.unibuc.fraudservice.exception.InvalidAuthenticationLocation;
import com.unibuc.fraudservice.exception.StoreClosedException;
import com.unibuc.fraudservice.exception.StoreFullException;
import com.unibuc.fraudservice.model.GateLocation;
import com.unibuc.fraudservice.model.PassAuthorizationRequest;
import com.unibuc.fraudservice.model.PassAuthorizationResponse;
import com.unibuc.fraudservice.proxy.CartProxy;
import com.unibuc.fraudservice.proxy.ProductProxy;
import com.unibuc.fraudservice.proxy.ShopperProxy;
import com.unibuc.fraudservice.proxy.StoreProxy;
import com.unibuc.fraudservice.service.FraudService;
import com.unibuc.identityservice.entity.Shopper;
import com.unibuc.productservice.entity.Product;
import com.unibuc.productservice.entity.Promotion;
import com.unibuc.storeservice.entity.Store;
import com.unibuc.storeservice.exception.StoreNotFoundException;
import com.unibuc.storeservice.model.City;

@Component
public class EntryGateway implements GatewayAction {

    private static final Logger log = LogManager.getLogger(EntryGateway.class);

    private StoreProxy storeProxy;

    private ShopperProxy shopperProxy;

    private ProductProxy productProxy;

    private FraudService fraudService;

    private CartProxy cartProxy;

    @Autowired
    public EntryGateway(StoreProxy storeProxy, ShopperProxy shopperProxy, ProductProxy productProxy, FraudService fraudService, CartProxy cartProxy) {
        this.storeProxy = storeProxy;
        this.shopperProxy = shopperProxy;
        this.productProxy = productProxy;
        this.fraudService = fraudService;
        this.cartProxy = cartProxy;
    }

    @PostMapping
    public PassAuthorizationResponse passAuthorization(@RequestBody PassAuthorizationRequest request) {

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
        Boolean isStoreActive = store != null ? store.getActiveStatus() : false;
        if (!isStoreActive) {

            log.warn(String.format("Store with id: %s is INACTIVE at: %s. Pass authorization denied.", request.getStoreId(), request.getAuthenticationEvent().getTimestamp()));
            throw new InactiveStoreException();
        }

        /*
         * Retrieve the store tag to verify if store allows ENTRY authentication.
         */
        Boolean isStoreWithEntryAuthorization = store.getStoreTag() == City.StoreTag.ENTRY_PASS || store.getStoreTag() == City.StoreTag.ENTRY_EXIT_PASS;
        if (!isStoreWithEntryAuthorization) {

            log.warn(String.format("Store with id: %s does not allow ENTRY_PASS. Pass authorization denied.", request.getStoreId()));
            throw new InvalidAuthenticationLocation(GateLocation.ENTRY.name());
        }

        /*
         * Check if request timestamp (day and time) is in store hours working interval.
         */
        Boolean isStoreOpen = storeProxy.isStoreOpen(request.getStoreId(), request.getAuthenticationEvent().getTimestamp());
        if (!isStoreOpen) {

            log.warn(String.format("Store with id: %s is CLOSED at: %s. Pass authorization denied.", request.getStoreId(), request.getAuthenticationEvent().getTimestamp()));

            /*
             * If store is closed, return the closest alternative open stores.
             */
            List<Store> nearestOpenStores = storeProxy.getNearestOpenStores(request.getStoreId(), request.getAuthenticationEvent().getTimestamp());
            List<String> nearestOpenStoresNames = nearestOpenStores.stream().map(s -> String.format("%s (%s)", s.getName(), s.getAddress())).collect(Collectors.toList());
            throw new StoreClosedException("Nearest OPEN stores: " + nearestOpenStoresNames);
        }

        /*
         * If store is FULL, don't allow entrance.
         */
        Boolean isStoreFull = storeProxy.isStoreFull(store.getId());
        if (isStoreFull) {
            log.warn("Store with id: " + request.getStoreId() + "is full at: " + request.getAuthenticationEvent().getTimestamp());
            throw new StoreFullException("Store is currently full. Please wait a couple minutes.");
        } else {
            /*
             * Increase current capacity for store.
             */
            log.info("Incrementing current store capacity for store with id: " + store.getId());
            store.setCurrentCapacity(store.getCurrentCapacity() + 1);
            storeProxy.updateStore(store.getId().toString(), store);
        }

        /*
         * If existing customer, get current promotions (if any) for shopper's saved products.
         */
        Shopper shopper = shopperProxy.retrieveShopper(request.getShopperId());
        List<Long> shopperSavedProductsIds = shopper.getFavouriteProducts().stream().map(Product::getId).collect(Collectors.toList());
        List<Promotion> promotionsForShopperSavedProducts = productProxy.retrievePromotionsForProducts(shopperSavedProductsIds);
        String currentPromotionsMessage = fraudService.getCurrentPromotionsMessage(promotionsForShopperSavedProducts);

        /*
        Check if it is shopper's birthday and their first shopping session today.
         */
        Boolean isShopperBirthday = shopperProxy.isShopperBirthday(shopper.getId());
        Boolean isShopperFirstSession = cartProxy.retrieveCartsForCustomerOnDate(shopper.getId(), Calendar.getInstance().getTime()).isEmpty();
        String shopperMessage = fraudService.getShopperMessage(shopper.getFirstName(), isShopperBirthday, isShopperFirstSession, GateLocation.ENTRY);

        /*
         * Generate empty cart for customer.
         */
        Cart emptyCart = cartProxy.createCart(shopper.getId(), request.getAuthenticationEvent().getId());
        log.info("Initialized empty cart: " + emptyCart + " for customer with id: " + shopper.getBonusPoints() + " and session with id: " + request.getAuthenticationEvent().getId());

        return new PassAuthorizationResponseBuilder()
                .withIsShopperAuthorized(true)
                .withShopperMessage(shopperMessage)
                .withCurrentPromotionsMessage(currentPromotionsMessage)
                .withAuthenticationEventId(request.getAuthenticationEvent().getId())
                .build();
    }
}
