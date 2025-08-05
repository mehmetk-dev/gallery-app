package com.mehmetkerem.galleryApp.mapper;

import com.mehmetkerem.galleryApp.dto.request.GalleristRequest;
import com.mehmetkerem.galleryApp.dto.response.GalleristResponse;
import com.mehmetkerem.galleryApp.models.Gallerist;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface GalleristMapper {
    Gallerist toEntity(GalleristRequest request);

    GalleristResponse toResponse(Gallerist gallerist);

    void updateFromRequest(GalleristRequest request, @MappingTarget Gallerist gallerist);

}
