package com.unibuc.productservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unibuc.productservice.model.PromotionType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bonuspromotions")
public class BonusPromotion extends Promotion {

    @Column(name = "bonus_points_earned")
    private Double bonusPointsEarned;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="product_id", nullable=false)
    protected Product product;

    @Override
    public String toString() {
        return String.format("Buy  %d x %s and you get %s bonus points!", minQuantity, getProduct().getName(), bonusPointsEarned);
    }
}
