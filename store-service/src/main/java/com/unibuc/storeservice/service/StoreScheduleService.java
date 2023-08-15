package com.unibuc.storeservice.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.unibuc.storeservice.entity.StoreSchedule;

public interface StoreScheduleService {

	List<StoreSchedule> getAll();
	List<StoreSchedule> getByStoreId(Long storeId);
	StoreSchedule updateStoreSchedule(Long storeScheduleId, StoreSchedule storeSchedule);

	Boolean isStoreOpen(Long storeId, Date timestamp) throws ParseException;
}
