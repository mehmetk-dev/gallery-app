package com.mehmetkerem.galleryApp.service;

import com.mehmetkerem.galleryApp.dto.request.GalleristCarRequest;
import com.mehmetkerem.galleryApp.dto.response.GalleristCarResponse;

public interface IGalleristCarService extends IGenericService<GalleristCarResponse,GalleristCarRequest>{

    GalleristCarResponse saveGalleristCar(GalleristCarRequest request);

}
