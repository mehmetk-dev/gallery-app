package com.mehmetkerem.galleryApp.controller.impl;

import com.mehmetkerem.galleryApp.controller.IRestAddressController;
import com.mehmetkerem.galleryApp.controller.RestBaseController;
import com.mehmetkerem.galleryApp.controller.RootEntity;
import com.mehmetkerem.galleryApp.dto.request.AddressRequest;
import com.mehmetkerem.galleryApp.dto.response.AddressResponse;
import com.mehmetkerem.galleryApp.service.IAddressService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController {

    private final IAddressService addressService;

    public RestAddressControllerImpl(IAddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/save")
    @Override
    public RootEntity<AddressResponse> saveAddress(@RequestBody AddressRequest request) {
        return ok(addressService.saveAddress(request));
    }

    @GetMapping("/{id}")
    @Override
    public RootEntity<AddressResponse> getAddressById(@PathVariable("id") Long id) {
        return ok(addressService.getById(id));
    }

    @PutMapping("/{id}")
    @Override
    public RootEntity<AddressResponse> updateAddress(@PathVariable("id") Long id, @RequestBody AddressRequest request) {
        return ok(addressService.update(id, request));
    }

    @DeleteMapping("{/id}")
    @Override
    public void deleteAddress(@PathVariable("id") Long id) {
        addressService.delete(id);
    }
}
