package com.unibuc.storeservice.serviceimpl;

import com.unibuc.storeservice.entity.Store;
import com.unibuc.storeservice.exception.StoreNotFoundException;
import com.unibuc.storeservice.repository.StoreRepository;
import com.unibuc.storeservice.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BaseStoreService implements StoreService {

    private StoreRepository storeRepository;

    @Autowired
    public BaseStoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Store addStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public List<Store> getAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store getStore(Long storeId) {
        Optional<Store> store = storeRepository.findById(storeId);
        if (store.isPresent())
            return store.get();
        else
            throw new StoreNotFoundException(String.format("Store with storeId: %s not found.", storeId));
    }

    @Override
    public Store updateStore(Long storeId, Store s) {
        Optional<Store> oldStore = storeRepository.findById(storeId);
        if (oldStore.isPresent()) {

            Store store = oldStore.get();
            store.setCurrentCapacity(s.getCurrentCapacity());
            return storeRepository.save(store);
        } else
            throw new StoreNotFoundException(String.format("Store with store id: %s not found.", storeId));
    }

    @Override
    public void deleteStore(Long storeId) {
        storeRepository.deleteById(storeId);
    }

    @Override
    public List<Store> getClosestStores(Long storeId) {

        Optional<Store> store = storeRepository.findById(storeId);
        if (store.isPresent()) {
            Long storeDistrictNumber = store.get().getDistrictNumber();
            return storeRepository.findByDistrictNumber(storeDistrictNumber);
        }
        throw new StoreNotFoundException(String.format("Store with store id: %s not found.", storeId));
    }
}
