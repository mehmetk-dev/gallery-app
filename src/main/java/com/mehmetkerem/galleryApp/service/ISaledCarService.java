package com.mehmetkerem.galleryApp.service;

import com.mehmetkerem.galleryApp.dto.request.SaledCarRequest;
import com.mehmetkerem.galleryApp.dto.response.SaledCarResponse;

public interface ISaledCarService extends IGenericService<SaledCarResponse,SaledCarRequest>{

    SaledCarResponse buyCar(SaledCarRequest request);

}
