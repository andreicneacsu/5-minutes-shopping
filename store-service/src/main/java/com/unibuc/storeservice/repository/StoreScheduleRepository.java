package com.unibuc.storeservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unibuc.storeservice.entity.StoreSchedule;

public interface StoreScheduleRepository extends JpaRepository<StoreSchedule, Long> {

	@Query("SELECT s from StoreSchedule s WHERE s.storeId = ?1")
	Optional<StoreSchedule> findByStoreId(Long storeId);

	@Query("SELECT s from StoreSchedule s WHERE s.storeId = ?1 AND s.dayOfWeek = ?2")
	Optional<StoreSchedule> findByStoreIdAndDayOfWeek(Long storeId, String dayOfWeek);
}
