package com.mehmetkerem.galleryApp.service.impl;

import com.mehmetkerem.galleryApp.dto.response.CurrencyRatesResponse;
import com.mehmetkerem.galleryApp.exception.BaseException;
import com.mehmetkerem.galleryApp.exception.ErrorMessage;
import com.mehmetkerem.galleryApp.exception.MessageType;
import com.mehmetkerem.galleryApp.service.ICurrencyRatesService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService {

    //https://evds2.tcmb.gov.tr/service/evds/series=TP.DK.USD.A&startDate=01-08-2025&endDate=01-08-2025&type=json

    @Override
    public CurrencyRatesResponse getCurrency(String startDate, String endDate) {

        String rootUrl = "https://evds2.tcmb.gov.tr/service/evds/";
        String series = "TP.DK.USD.A";
        String type = "json";

        String endPoint = rootUrl + "series=" + series + "&startDate=" + startDate + "&endDate=" + endDate + "&type=" + type;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("key", "nvl7rTZXiS");

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<CurrencyRatesResponse> response = restTemplate.
                    exchange(endPoint, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<CurrencyRatesResponse>() {
                    });
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED, e.getMessage()));
        }
        return null;
    }
}
