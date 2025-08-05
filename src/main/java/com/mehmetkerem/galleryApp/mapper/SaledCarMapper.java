package com.mehmetkerem.galleryApp.mapper;

import com.mehmetkerem.galleryApp.dto.request.SaledCarRequest;
import com.mehmetkerem.galleryApp.dto.response.SaledCarResponse;
import com.mehmetkerem.galleryApp.models.SaledCar;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface SaledCarMapper {

    SaledCar toEntity(SaledCarRequest request);

    SaledCarResponse toResponse(SaledCar saledCar);

    void updateFromRequest(SaledCarRequest request, @MappingTarget SaledCar saledCar);

}
