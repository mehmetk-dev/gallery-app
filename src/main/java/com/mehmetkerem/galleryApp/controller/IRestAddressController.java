package com.mehmetkerem.galleryApp.controller;

import com.mehmetkerem.galleryApp.dto.request.AddressRequest;
import com.mehmetkerem.galleryApp.dto.response.AddressResponse;

public interface IRestAddressController {

    RootEntity<AddressResponse> saveAddress(AddressRequest request);
    RootEntity<AddressResponse> getAddressById(Long id);
    RootEntity<AddressResponse> updateAddress(Long id, AddressRequest request);
    void deleteAddress(Long id);
}
