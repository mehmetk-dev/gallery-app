package com.mehmetkerem.galleryApp.controller.impl;

import com.mehmetkerem.galleryApp.controller.IRestAccountController;
import com.mehmetkerem.galleryApp.controller.RestBaseController;
import com.mehmetkerem.galleryApp.controller.RootEntity;
import com.mehmetkerem.galleryApp.dto.request.AccountRequest;
import com.mehmetkerem.galleryApp.dto.response.AccountResponse;
import com.mehmetkerem.galleryApp.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/account")
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController {

    private final IAccountService accountService;

    public RestAccountControllerImpl(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/save")
    @Override
    public RootEntity<AccountResponse> saveAccount(@Valid @RequestBody AccountRequest request) {
        return ok(accountService.saveAccount(request));
    }

    @GetMapping("/{id}")
    @Override
    public RootEntity<AccountResponse> getAccountById(@PathVariable("id") Long id) {
        return ok(accountService.getById(id));
    }

    @PutMapping("/{id}")
    @Override
    public RootEntity<AccountResponse> updateAccount(@PathVariable("id") Long id,@RequestBody AccountRequest request) {
        return ok(accountService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteAccount(@PathVariable("id") Long id) {
        accountService.delete(id);
    }
}
