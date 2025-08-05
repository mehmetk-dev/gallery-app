package com.mehmetkerem.galleryApp.controller;

import com.mehmetkerem.galleryApp.dto.request.GalleristRequest;
import com.mehmetkerem.galleryApp.dto.response.GalleristResponse;

public interface IRestGalleristController {
    RootEntity<GalleristResponse> saveGallerist(GalleristRequest request);

    RootEntity<GalleristResponse> getGalleristById(Long id);

    RootEntity<GalleristResponse> updateGallerist(Long id, GalleristRequest request);

    void deleteGallerist(Long id);
}
