package com.unibuc.storeservice.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unibuc.storeservice.entity.Store;
import com.unibuc.storeservice.entity.StoreSchedule;
import com.unibuc.storeservice.exception.StoreScheduleNotFoundException;
import com.unibuc.storeservice.repository.StoreScheduleRepository;
import com.unibuc.storeservice.service.StoreScheduleService;

@Service
public class BaseStoreScheduleService implements StoreScheduleService  {

	private StoreScheduleRepository storeScheduleRepository;

	@Autowired
	public BaseStoreScheduleService(StoreScheduleRepository storeScheduleRepository) {
		this.storeScheduleRepository = storeScheduleRepository;
	}

	@Override
	public List<StoreSchedule> getAll() {
		return storeScheduleRepository.findAll();
	}

	@Override
	public StoreSchedule getByStoreId(Long storeId) {
		Optional<StoreSchedule> storeSchedule = storeScheduleRepository.findByStoreId(storeId);
		if (storeSchedule.isPresent())
			return storeSchedule.get();
		throw new StoreScheduleNotFoundException(String.format("Store schedule for store with id: %d not found.", storeId));
	}

	@Override
	public StoreSchedule updateStoreSchedule(Long storeScheduleId, StoreSchedule storeSchedule) {
		Optional<StoreSchedule> oldStoreSchedule = storeScheduleRepository.findById(storeScheduleId);
		if (oldStoreSchedule.isPresent()) {
			StoreSchedule s = oldStoreSchedule.get();
			s.setStartHour(storeSchedule.getStartHour());
			s.setEndHour(storeSchedule.getEndHour());
			s.setDayOfWeek(storeSchedule.getDayOfWeek());
			return storeScheduleRepository.save(s);
		}
		throw new StoreScheduleNotFoundException(String.format("Store schedule with id: %d not found.", storeScheduleId));
	}
}
