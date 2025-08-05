package com.mehmetkerem.galleryApp.controller.impl;

import com.mehmetkerem.galleryApp.controller.IRestCurrencyRatesController;
import com.mehmetkerem.galleryApp.controller.RestBaseController;
import com.mehmetkerem.galleryApp.controller.RootEntity;
import com.mehmetkerem.galleryApp.dto.response.CurrencyRatesResponse;
import com.mehmetkerem.galleryApp.service.ICurrencyRatesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/currency-rates")
public class RestCurrencyRatesController extends RestBaseController implements IRestCurrencyRatesController {

    private final ICurrencyRatesService currencyRatesService;

    public RestCurrencyRatesController(ICurrencyRatesService currencyRatesService) {
        this.currencyRatesService = currencyRatesService;
    }

    @GetMapping("/get")
    @Override
    public RootEntity<CurrencyRatesResponse> getCurrencyRates(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        return ok(currencyRatesService.getCurrency(startDate, endDate));
    }
}
