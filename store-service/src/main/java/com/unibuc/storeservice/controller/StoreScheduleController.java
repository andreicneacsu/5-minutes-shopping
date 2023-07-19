package com.unibuc.storeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unibuc.storeservice.entity.Store;
import com.unibuc.storeservice.entity.StoreSchedule;
import com.unibuc.storeservice.service.StoreScheduleService;

@RestController
@RequestMapping("/storeschedules")
public class StoreScheduleController {

	@Autowired
	private StoreScheduleService storeScheduleService;

	@PutMapping("/{id}")
	public StoreSchedule updateStoreSchedule(@PathVariable Long id, @RequestBody StoreSchedule updatedStoreSchedule) {
		return storeScheduleService.updateStoreSchedule(id, updatedStoreSchedule);
	}

	@GetMapping("/")
	public List<StoreSchedule> getAllStoreSchedules(){
		return storeScheduleService.getAll();
	}

	@GetMapping("/{storeId}")
	public StoreSchedule getStoreScheduleForStore(@PathVariable Long storeId){
		return storeScheduleService.getByStoreId(storeId);
	}
}
