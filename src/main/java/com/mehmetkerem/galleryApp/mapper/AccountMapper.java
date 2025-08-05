package com.mehmetkerem.galleryApp.mapper;

import com.mehmetkerem.galleryApp.dto.request.AccountRequest;
import com.mehmetkerem.galleryApp.dto.response.AccountResponse;
import com.mehmetkerem.galleryApp.models.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface AccountMapper {

    Account toEntity(AccountRequest request);

    AccountResponse toResponse(Account account);

    void updateFromRequest(AccountRequest request, @MappingTarget Account account);
}
