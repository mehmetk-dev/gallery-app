package com.mehmetkerem.galleryApp.controller;

import com.mehmetkerem.galleryApp.dto.response.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {
    RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate);

}
