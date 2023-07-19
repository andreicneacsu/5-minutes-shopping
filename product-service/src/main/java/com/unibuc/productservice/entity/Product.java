package com.unibuc.productservice.entity;

import java.util.List;


import com.unibuc.productservice.model.MeasureUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;

	@Column(name = "price_per_measure_unit")
	private Double pricePerMeasureUnit;

	@Column(name = "name")
	private String name;

	@Column(name = "unity_of_measure")
	@Enumerated(EnumType.STRING)
	private MeasureUnit unityOfMeasure;

	@OneToMany(mappedBy="product")
	private List<BonusPromotion> bonusPromotions;

	@OneToMany(mappedBy="product")
	private List<DiscountPromotion> discountPromotions;
}
