package com.mehmetkerem.galleryApp.service;

import com.mehmetkerem.galleryApp.dto.request.AddressRequest;
import com.mehmetkerem.galleryApp.dto.response.AddressResponse;

public interface IAddressService extends IGenericService<AddressResponse,AddressRequest>{

    AddressResponse saveAddress(AddressRequest request);

}
