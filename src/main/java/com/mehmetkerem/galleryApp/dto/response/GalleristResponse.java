package com.mehmetkerem.galleryApp.dto.response;

import com.mehmetkerem.galleryApp.dto.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GalleristResponse extends BaseResponse {

    private String firstName;

    private String lastName;

    private AddressResponse address;

}
