package com.unibuc.storeservice.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unibuc.storeservice.model.DayOfWeek;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store_schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "store_id")
	private Long storeId;

	@Column(name = "day_of_week")
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayOfWeek;

	@Column(name = "start_hour")
	@JsonFormat(pattern="HH:mm")
	@DateTimeFormat(pattern="HH:mm")
	private Date startHour;

	@Column(name = "end_hour")
	@JsonFormat(pattern="HH:mm")
	@DateTimeFormat(pattern="HH:mm")
	private Date endHour;
}
