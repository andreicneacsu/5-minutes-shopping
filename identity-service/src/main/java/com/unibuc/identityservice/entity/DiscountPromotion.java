package com.unibuc.identityservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "discountpromotions")
public class DiscountPromotion extends Promotion {

    @Column(name = "discount_percent")
    private Double discountPercent;

    @Override
    public String toString() {
        return String.format("Buy  %d products and you get %s% discount!", minQuantity, discountPercent);
    }
}
