package com.mehmetkerem.galleryApp.service;

import com.mehmetkerem.galleryApp.dto.request.CarRequest;
import com.mehmetkerem.galleryApp.dto.response.CarResponse;

public interface ICarService extends IGenericService<CarResponse,CarRequest>{

    CarResponse saveCar(CarRequest request);

}
