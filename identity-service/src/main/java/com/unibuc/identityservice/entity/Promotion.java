package com.unibuc.identityservice.entity;

import java.util.Date;

import com.unibuc.identityservice.model.PromotionType;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class Promotion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "promotion_id")
	protected Long id;

	@Column(name = "product_id")
	protected Long productId;

	@Column(name = "min_quantity")
	protected Double minQuantity;

	@Column(name = "starts_on")
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	protected Date startsOn;

	@Column(name = "expires_on")
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	protected Date expiresOn;

	@Column(name = "promotion_type")
	@Enumerated(EnumType.STRING)
	protected PromotionType promotionType;
}
