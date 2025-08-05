package com.mehmetkerem.galleryApp.mapper;

import com.mehmetkerem.galleryApp.dto.request.GalleristCarRequest;
import com.mehmetkerem.galleryApp.dto.response.GalleristCarResponse;
import com.mehmetkerem.galleryApp.models.GalleristCar;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface GalleristCarMapper {

    GalleristCar toEntity(GalleristCarRequest request);

    GalleristCarResponse toResponse(GalleristCar galleristCar);

    void updateFromRequest(GalleristCarRequest request, @MappingTarget GalleristCar galleristCar);

}
