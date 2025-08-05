package com.mehmetkerem.galleryApp.controller.impl;

import com.mehmetkerem.galleryApp.controller.IRestCustomerController;
import com.mehmetkerem.galleryApp.controller.RestBaseController;
import com.mehmetkerem.galleryApp.controller.RootEntity;
import com.mehmetkerem.galleryApp.dto.request.CustomerRequest;
import com.mehmetkerem.galleryApp.dto.response.CustomerResponse;
import com.mehmetkerem.galleryApp.service.ICustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customer")
public class RestCustomerControllerImpl extends RestBaseController implements IRestCustomerController {

    private final ICustomerService customerService;

    public RestCustomerControllerImpl(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    @Override
    public RootEntity<CustomerResponse> saveCustomer(@RequestBody CustomerRequest request) {
        return ok(customerService.saveCustomer(request));
    }

    @GetMapping("/{id}")
    @Override
    public RootEntity<CustomerResponse> getCustomerById(@PathVariable("id") Long id) {
        return ok(customerService.getById(id));
    }

    @PutMapping("/{id}")
    @Override
    public RootEntity<CustomerResponse> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerRequest request) {
        return ok(customerService.update(id, request));
    }

    @DeleteMapping("{/id}")
    @Override
    public void deleteCustomer(@PathVariable("id") Long id) {
        customerService.delete(id);
    }
}
