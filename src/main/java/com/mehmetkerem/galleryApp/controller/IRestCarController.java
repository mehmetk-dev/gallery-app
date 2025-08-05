package com.mehmetkerem.galleryApp.controller;

import com.mehmetkerem.galleryApp.dto.request.CarRequest;
import com.mehmetkerem.galleryApp.dto.response.CarResponse;

public interface IRestCarController {

    RootEntity<CarResponse> saveCar(CarRequest request);
    RootEntity<CarResponse> getCarById(Long id);
    RootEntity<CarResponse> updateCar(Long id, CarRequest request);
    void deleteCar(Long id);
}
