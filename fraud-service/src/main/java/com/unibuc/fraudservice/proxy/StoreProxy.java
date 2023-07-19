package com.unibuc.fraudservice.proxy;

import com.unibuc.storeservice.entity.Store;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@FeignClient(name="store-service")
@RibbonClient(name="store-service")
public interface StoreProxy {

    @GetMapping("/stores")
    List<Store> retrieveAllStores();

    @GetMapping("/stores/{id}")
    Store retrieveStore(@PathVariable Long id);

    @GetMapping("/stores/{storeId}/open")
    Boolean isStoreOpen(@PathVariable Long storeId, @RequestParam Date dateWithTime);

    @GetMapping("/stores/{storeId}/full")
    Boolean isStoreFull(@PathVariable Long storeId);

    @GetMapping("/stores/{storeId}/nearestopen")
    List<Store> getNearestOpenStores(@PathVariable Long storeId, @RequestParam Date dateWithTime);

    @PutMapping("/{id}")
    Long updateStore(@PathVariable String id, @RequestBody Store updatedStore);
}
