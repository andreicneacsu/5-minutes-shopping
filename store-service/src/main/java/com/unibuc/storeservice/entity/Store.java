package com.unibuc.storeservice.entity;

import java.util.Date;

import com.unibuc.storeservice.model.City;

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
@Table(name = "stores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    @Enumerated(EnumType.STRING)
    private City city;

    @Column(name = "address")
    private String address;

    @Column(name = "retailer_id")
    private Long retailerId;

    @Column(name = "launch_date")
    private Date launchDate;

    @Column(name = "active_status")
    private Boolean activeStatus;

    @Column(name = "store_tag")
    @Enumerated(EnumType.STRING)
    private City.StoreTag storeTag;

    @Column(name = "district_number")
    private Long districtNumber;

    @Column(name = "current_capacity")
    private Long currentCapacity;

    @Column(name = "bonus_points_discount")
    private Long bonusPointsDiscount;

    @Column(name = "max_capacity")
    private Long maxCapacity;



}
