package com.mehmetkerem.galleryApp.controller;

import com.mehmetkerem.galleryApp.dto.request.AccountRequest;
import com.mehmetkerem.galleryApp.dto.response.AccountResponse;

public interface IRestAccountController {

    RootEntity<AccountResponse> saveAccount(AccountRequest request);
    RootEntity<AccountResponse> getAccountById(Long id);
    RootEntity<AccountResponse> updateAccount(Long id,AccountRequest request);
    void deleteAccount(Long id);
}
