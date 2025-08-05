package com.mehmetkerem.galleryApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "saled_car",
        uniqueConstraints = {@UniqueConstraint(columnNames =
                {"gallerist_id", "car_id", "customer_id"}, name = "un_gallerist_saled")})
public class SaledCar extends BaseEntity {

    @ManyToOne
    private Gallerist gallerist;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Car car;
}
