package com.mehmetkerem.galleryApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address extends BaseEntity{

    private String city;

    private String district;

    private String neighborhood;

    private String street;
}
