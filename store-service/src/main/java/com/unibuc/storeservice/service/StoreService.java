package com.unibuc.storeservice.service;

import com.unibuc.storeservice.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreService {

    Store addStore(Store store);
    List<Store> getAll();
    Store getStore(Long storeId);
    Store updateStore(Long storeId, Store s);
    void deleteStore(Long storeId);
    List<Store> getClosestStores(Long storeId);
}
