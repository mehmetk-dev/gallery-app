package com.mehmetkerem.galleryApp.dto.response;

import com.mehmetkerem.galleryApp.dto.BaseResponse;
import com.mehmetkerem.galleryApp.enums.CarStatusType;
import com.mehmetkerem.galleryApp.enums.CurrencyType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CarResponse extends BaseResponse {

    private String plaka;

    private String brand;

    private String model;

    private Integer productionYear;

    private BigDecimal price;

    private CurrencyType currencyType;

    private BigDecimal damagePrice;

    private CarStatusType carStatusType;
}
