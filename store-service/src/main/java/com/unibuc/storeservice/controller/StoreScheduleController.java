package com.unibuc.storeservice.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.unibuc.storeservice.entity.StoreSchedule;
import com.unibuc.storeservice.service.StoreScheduleService;

@RestController
@RequestMapping("/storeschedules")
public class StoreScheduleController {

	private static final Logger log = LogManager.getLogger(StoreScheduleController.class);

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
	public List<StoreSchedule> getStoreScheduleForStore(@PathVariable Long storeId){
		return storeScheduleService.getByStoreId(storeId);
	}

	@GetMapping("/{storeId}/open")
	public Boolean isStoreOpen(@PathVariable Long storeId, @RequestParam String dateWithTime) throws ParseException {
		log.info(String.format("Checking if store with id: %d is open on: %s", storeId, dateWithTime));
		Date date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(dateWithTime);
		return storeScheduleService.isStoreOpen(storeId, date);
	}
}
