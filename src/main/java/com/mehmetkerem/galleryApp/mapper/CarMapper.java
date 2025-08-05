package com.mehmetkerem.galleryApp.mapper;

import com.mehmetkerem.galleryApp.dto.request.CarRequest;
import com.mehmetkerem.galleryApp.dto.response.CarResponse;
import com.mehmetkerem.galleryApp.models.Car;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface CarMapper {

    Car toEntity(CarRequest request);

    CarResponse toResponse(Car car);

    void updateFromRequest(CarRequest request, @MappingTarget Car car);

}
