package com.unibuc.identityservice.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unibuc.identityservice.model.MeasureUnit;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
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

	@ManyToMany(mappedBy = "shoppingList")
	@JsonIgnore
	private List<Shopper> shopperLists = new ArrayList<>();

	@ManyToMany(mappedBy = "favouriteProducts")
	@JsonIgnore
	private List<Shopper> shopperFavourites = new ArrayList<>();
}
