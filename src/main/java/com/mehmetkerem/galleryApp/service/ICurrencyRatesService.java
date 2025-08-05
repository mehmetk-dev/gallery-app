package com.mehmetkerem.galleryApp.service;

import com.mehmetkerem.galleryApp.dto.response.CurrencyRatesResponse;

public interface ICurrencyRatesService {

    CurrencyRatesResponse getCurrency(String startDate,String endDate);
}
