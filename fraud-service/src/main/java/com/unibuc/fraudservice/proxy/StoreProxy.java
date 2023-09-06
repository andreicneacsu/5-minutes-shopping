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
    Store retrieveStore(@PathVariable(value = "id") Long id);

    @GetMapping("/storeschedules/{storeId}/open")
    Boolean isStoreOpen(@PathVariable(value = "storeId") Long storeId, @RequestParam(value = "dateWithTime") String dateWithTime);

    @GetMapping("/stores/{storeId}/full")
    Boolean isStoreFull(@PathVariable(value = "storeId") Long storeId);

    @GetMapping("/stores/{storeId}/nearestopen")
    List<Store> getNearestOpenStores(@PathVariable(value = "storeId") Long storeId, @RequestParam(value = "dateWithTime") String dateWithTime);

    @PutMapping("/stores/{id}")
    Store updateStore(@PathVariable(value = "id") Long id, @RequestBody Store updatedStore);
}
