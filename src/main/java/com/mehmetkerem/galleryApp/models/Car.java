package com.mehmetkerem.galleryApp.models;

import com.mehmetkerem.galleryApp.enums.CarStatusType;
import com.mehmetkerem.galleryApp.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car")
public class Car extends BaseEntity {

    private String plaka;

    private String brand;

    private String model;

    @Column(name = "production_year")
    private Integer productionYear;

    private BigDecimal price;

    @Column(name = "currency_type")
    @Enumerated(value = EnumType.STRING)
    private CurrencyType currencyType;

    @Column(name = "damage_price")
    private BigDecimal damagePrice;

    @Column(name = "car_status")
    @Enumerated(value = EnumType.STRING)
    private CarStatusType carStatusType;
}
