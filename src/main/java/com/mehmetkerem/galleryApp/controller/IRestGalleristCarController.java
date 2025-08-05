package com.mehmetkerem.galleryApp.controller;

import com.mehmetkerem.galleryApp.dto.request.CustomerRequest;
import com.mehmetkerem.galleryApp.dto.request.GalleristCarRequest;
import com.mehmetkerem.galleryApp.dto.response.CustomerResponse;
import com.mehmetkerem.galleryApp.dto.response.GalleristCarResponse;

public interface IRestGalleristCarController {

    RootEntity<GalleristCarResponse> saveGalleristCar(GalleristCarRequest request);

    RootEntity<GalleristCarResponse> getGalleristCarById(Long id);

    RootEntity<GalleristCarResponse> updateGalleristCar(Long id, GalleristCarRequest request);

    void deleteGalleristCar(Long id);
}
