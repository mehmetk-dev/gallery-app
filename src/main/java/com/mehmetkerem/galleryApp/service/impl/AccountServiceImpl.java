package com.mehmetkerem.galleryApp.service.impl;

import com.mehmetkerem.galleryApp.dto.request.AccountRequest;
import com.mehmetkerem.galleryApp.dto.response.AccountResponse;
import com.mehmetkerem.galleryApp.exception.BaseException;
import com.mehmetkerem.galleryApp.exception.ErrorMessage;
import com.mehmetkerem.galleryApp.exception.MessageType;
import com.mehmetkerem.galleryApp.mapper.AccountMapper;
import com.mehmetkerem.galleryApp.models.Account;
import com.mehmetkerem.galleryApp.repository.AccountRepository;
import com.mehmetkerem.galleryApp.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    private Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString())));
    }

    private Account createAccount(AccountRequest request) {
        Account account = new Account();
        account.setCreateDate(new Date());
        BeanUtils.copyProperties(request, account);
        return account;
    }

    @Override
    public AccountResponse saveAccount(AccountRequest request) {
        Account savedAccount = accountRepository.save(createAccount(request));
        AccountResponse accountResponse = new AccountResponse();
        BeanUtils.copyProperties(savedAccount, accountResponse);
        return accountResponse;
    }

    @Override
    public void delete(long id) {
        accountRepository.delete(getAccountById(id));
    }

    @Override
    public AccountResponse update(Long id, AccountRequest request) {
        Account account = getAccountById(id);
        accountMapper.updateFromRequest(request, account);
        accountRepository.save(account);
        return accountMapper.toResponse(account);
    }

    @Override
    public AccountResponse getById(Long id) {
        return accountMapper.toResponse(getAccountById(id));
    }
}
