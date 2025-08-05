package com.mehmetkerem.galleryApp.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaledCarRequest {

    private Long carId;
    private Long galleristId;
    private Long customerId;
}
