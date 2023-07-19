package com.unibuc.storeservice.service;

import java.util.List;

import com.unibuc.storeservice.entity.StoreSchedule;

public interface StoreScheduleService {

	List<StoreSchedule> getAll();
	StoreSchedule getByStoreId(Long storeId);
	StoreSchedule updateStoreSchedule(Long storeScheduleId, StoreSchedule storeSchedule);
}
