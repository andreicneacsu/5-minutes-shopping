package com.unibuc.storeservice.serviceimpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import com.unibuc.storeservice.controller.StoreScheduleController;
import com.unibuc.storeservice.model.DayOfWeek;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unibuc.storeservice.entity.StoreSchedule;
import com.unibuc.storeservice.exception.StoreScheduleNotFoundException;
import com.unibuc.storeservice.repository.StoreScheduleRepository;
import com.unibuc.storeservice.service.StoreScheduleService;

@Service
public class BaseStoreScheduleService implements StoreScheduleService  {

	private static final Logger log = LogManager.getLogger(BaseStoreScheduleService.class);

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
	public List<StoreSchedule> getByStoreId(Long storeId) {
		List<StoreSchedule> storeSchedules = storeScheduleRepository.findByStoreId(storeId);
		if (!storeSchedules.isEmpty())
			return storeSchedules;
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

	@Override
	public Boolean isStoreOpen(Long storeId, Date timestamp) throws ParseException {

		DateFormat formatter = new SimpleDateFormat("EEEE");
		DayOfWeek dayOfWeek = DayOfWeek.valueOf(formatter.format(timestamp).toUpperCase());

		Optional<StoreSchedule> storeSchedule = storeScheduleRepository.findByStoreIdAndDayOfWeek(storeId, dayOfWeek);
		if (storeSchedule.isPresent()) {
			StoreSchedule schedule = storeSchedule.get();
			DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			String startHour = dateFormat.format(schedule.getStartHour());
			String endHour = dateFormat.format(schedule.getEndHour());
			String currentTime = dateFormat.format(timestamp);

			log.info(String.format("Store schedule found for %s starting at %s and ending at %s", dayOfWeek, startHour, endHour));

			log.info(String.format("Checking if store is open on %s at %s...", dayOfWeek, currentTime));
			return dateFormat.parse(startHour).before(dateFormat.parse(currentTime))
					&& dateFormat.parse(endHour).after(dateFormat.parse(currentTime));
		}
		throw new StoreScheduleNotFoundException(String.format("Store schedule for store with id: %d not found.", storeId));	}
}
