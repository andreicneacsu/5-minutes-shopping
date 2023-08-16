package com.unibuc.identityservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bonuspromotions")
public class BonusPromotion extends Promotion {

    @Column(name = "bonus_points_earned")
    private Double bonusPointsEarned;

    @Override
    public String toString() {
        return String.format("Buy  %d products and you get %s bonus points!", minQuantity, bonusPointsEarned);
    }
}
