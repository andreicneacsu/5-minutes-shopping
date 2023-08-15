package com.unibuc.storeservice.controller;

import com.unibuc.storeservice.entity.Store;
import com.unibuc.storeservice.exception.StoreNotFoundException;
import com.unibuc.storeservice.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/")
    public List<Store> getAllStores(){
        return storeService.getAll();
    }

    @PostMapping("/")
    public Store addStore(@RequestBody Store store){
        return storeService.addStore(store);
    }

    @GetMapping("/{id}")
    public Store getStore(@PathVariable Long id) {
        return storeService.getStore(id);
    }

    @GetMapping("/{id}/nearestopen")
    public List<Store> getClosestStores(@PathVariable Long id) {
        return storeService.getClosestStores(id);
    }

    @PutMapping("/{id}")
    public Store updateStore(@PathVariable Long id, @RequestBody Store updatedStore) {
        return storeService.updateStore(id, updatedStore);
    }

    @GetMapping("/{id}/full")
    public Boolean isStoreFull(@PathVariable Long id) {
        Store store = storeService.getStore(id);
        return store.getCurrentCapacity() == store.getMaxCapacity();
    }

    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
    }
}
