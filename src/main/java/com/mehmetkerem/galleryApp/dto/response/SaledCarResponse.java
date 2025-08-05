package com.mehmetkerem.galleryApp.dto.response;

import com.mehmetkerem.galleryApp.dto.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaledCarResponse extends BaseResponse {

    private CarResponse car;
    private GalleristResponse gallerist;
    private CustomerResponse customer;
}
