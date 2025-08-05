package com.mehmetkerem.galleryApp.controller;

import com.mehmetkerem.galleryApp.dto.request.CustomerRequest;
import com.mehmetkerem.galleryApp.dto.response.CustomerResponse;

public interface IRestCustomerController {

    RootEntity<CustomerResponse> saveCustomer(CustomerRequest request);
    RootEntity<CustomerResponse> getCustomerById(Long id);
    RootEntity<CustomerResponse> updateCustomer(Long id, CustomerRequest request);
    void deleteCustomer(Long id);
}
