package com.mehmetkerem.galleryApp.mapper;

import com.mehmetkerem.galleryApp.dto.request.CustomerRequest;
import com.mehmetkerem.galleryApp.dto.response.CustomerResponse;
import com.mehmetkerem.galleryApp.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface CustomerMapper {

    CustomerResponse toResponse(Customer customer);

    Customer toEntity(CustomerRequest request);

    void updateFromRequest(CustomerRequest request, @MappingTarget Customer customer);

}
