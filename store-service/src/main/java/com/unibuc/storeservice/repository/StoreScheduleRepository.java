package com.unibuc.storeservice.repository;

import java.util.List;
import java.util.Optional;

import com.unibuc.storeservice.model.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unibuc.storeservice.entity.StoreSchedule;

public interface StoreScheduleRepository extends JpaRepository<StoreSchedule, Long> {

	@Query("SELECT s from StoreSchedule s WHERE s.storeId = ?1")
	List<StoreSchedule> findByStoreId(Long storeId);

	@Query("SELECT s from StoreSchedule s WHERE s.storeId = ?1 AND s.dayOfWeek = ?2")
	Optional<StoreSchedule> findByStoreIdAndDayOfWeek(Long storeId, DayOfWeek dayOfWeek);
}
