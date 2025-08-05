package com.mehmetkerem.galleryApp.service;

import com.mehmetkerem.galleryApp.dto.request.AccountRequest;
import com.mehmetkerem.galleryApp.dto.response.AccountResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAccountService extends IGenericService<AccountResponse,AccountRequest>{

    AccountResponse saveAccount(AccountRequest request);

}
