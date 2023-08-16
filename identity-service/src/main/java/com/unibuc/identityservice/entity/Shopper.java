package com.unibuc.identityservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "shoppers")
@Getter
@Setter
public class Shopper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopper_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    @JsonFormat(pattern="dd/MM/yyyy")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date birthDate;

    @Column(name = "bonus_points")
    private Double bonusPoints;

    @ManyToMany
    @JoinTable(
            name = "product_favourites",
            joinColumns = @JoinColumn(name = "shopper_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> favouriteProducts;

    @ManyToMany
    @JoinTable(
            name = "shopping_lists",
            joinColumns = @JoinColumn(name = "shopper_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> shoppingList;
}
