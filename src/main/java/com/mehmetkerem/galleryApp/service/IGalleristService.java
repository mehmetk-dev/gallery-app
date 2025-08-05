package com.mehmetkerem.galleryApp.service;


import com.mehmetkerem.galleryApp.dto.request.GalleristRequest;
import com.mehmetkerem.galleryApp.dto.response.GalleristResponse;

public interface IGalleristService extends IGenericService<GalleristResponse,GalleristRequest> {

    GalleristResponse saveGallerist(GalleristRequest request);
}