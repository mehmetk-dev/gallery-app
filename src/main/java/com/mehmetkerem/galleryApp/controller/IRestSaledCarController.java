package com.mehmetkerem.galleryApp.controller;

import com.mehmetkerem.galleryApp.dto.request.SaledCarRequest;
import com.mehmetkerem.galleryApp.dto.response.SaledCarResponse;

public interface IRestSaledCarController {

    RootEntity<SaledCarResponse> buyCar(SaledCarRequest request);

    RootEntity<SaledCarResponse> getSaledCarById(Long id);

    RootEntity<SaledCarResponse> updateSaledCar(Long id, SaledCarRequest request);

    void deleteSaledCar(Long id);
}
