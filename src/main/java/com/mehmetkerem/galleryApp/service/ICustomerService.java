package com.mehmetkerem.galleryApp.service;

import com.mehmetkerem.galleryApp.dto.request.CustomerRequest;
import com.mehmetkerem.galleryApp.dto.response.CustomerResponse;
import com.mehmetkerem.galleryApp.models.Customer;

public interface ICustomerService extends IGenericService<CustomerResponse,CustomerRequest>{

    CustomerResponse saveCustomer(CustomerRequest request);

    Customer getCustomerById(Long id);


}
