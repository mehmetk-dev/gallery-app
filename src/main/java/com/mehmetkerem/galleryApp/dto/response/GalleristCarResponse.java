package com.mehmetkerem.galleryApp.dto.response;

import com.mehmetkerem.galleryApp.dto.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GalleristCarResponse extends BaseResponse {

    private CarResponse carResponse;
    private GalleristResponse galleristResponse;
}
