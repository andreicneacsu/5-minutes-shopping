package com.unibuc.productservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unibuc.productservice.model.PromotionType;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "discountpromotions")
public class DiscountPromotion extends Promotion {

    @Column(name = "discount_percent")
    private Double discountPercent;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="product_id", nullable=false)
    protected Product product;

    @Override
    public String toString() {
        return String.format("Buy  %d x %s and you get %s% discount!", minQuantity, getProduct().getName(), discountPercent);
    }
}
