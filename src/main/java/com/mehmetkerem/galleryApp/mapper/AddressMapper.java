package com.mehmetkerem.galleryApp.mapper;

import com.mehmetkerem.galleryApp.dto.request.AddressRequest;
import com.mehmetkerem.galleryApp.dto.response.AddressResponse;
import com.mehmetkerem.galleryApp.models.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface AddressMapper {

    Address toEntity(AddressRequest request);

    AddressResponse toResponse(Address address);

    void updateFromRequest(AddressRequest request, @MappingTarget Address address);
}

