package com.mehmetkerem.galleryApp.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CurrencyRatesResponse {

    private Integer totalCount;
    private List<CurrencyRatesItems> items;
}
