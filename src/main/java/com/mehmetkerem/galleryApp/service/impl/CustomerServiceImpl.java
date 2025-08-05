package com.mehmetkerem.galleryApp.service.impl;

import com.mehmetkerem.galleryApp.dto.request.CustomerRequest;
import com.mehmetkerem.galleryApp.dto.response.AccountResponse;
import com.mehmetkerem.galleryApp.dto.response.AddressResponse;
import com.mehmetkerem.galleryApp.dto.response.CustomerResponse;
import com.mehmetkerem.galleryApp.exception.BaseException;
import com.mehmetkerem.galleryApp.exception.ErrorMessage;
import com.mehmetkerem.galleryApp.exception.MessageType;
import com.mehmetkerem.galleryApp.mapper.CustomerMapper;
import com.mehmetkerem.galleryApp.models.Account;
import com.mehmetkerem.galleryApp.models.Address;
import com.mehmetkerem.galleryApp.models.Customer;
import com.mehmetkerem.galleryApp.repository.AccountRepository;
import com.mehmetkerem.galleryApp.repository.AddressRepository;
import com.mehmetkerem.galleryApp.repository.CustomerRepository;
import com.mehmetkerem.galleryApp.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final AccountRepository accountRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, AddressRepository addressRepository, AccountRepository accountRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.accountRepository = accountRepository;
        this.customerMapper = customerMapper;
    }

    private Customer createCustomer(CustomerRequest request) {

        Optional<Address> optAddress = addressRepository.findById(request.getAddressId());
        if (optAddress.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, request.getAddressId().toString()));
        }
        Optional<Account> optAccount = accountRepository.findById(request.getAccountId());
        if (optAccount.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, request.getAccountId().toString()));
        }

        Customer customer = new Customer();
        customer.setCreateDate(new Date());
        BeanUtils.copyProperties(request, customer);

        customer.setAccount(optAccount.get());
        customer.setAddress(optAddress.get());

        return customer;
    }


    @Override
    public CustomerResponse saveCustomer(CustomerRequest request) {
        Customer savedCustomer = customerRepository.save(createCustomer(request));

        CustomerResponse customerResponse = new CustomerResponse();
        AddressResponse addressResponse = new AddressResponse();
        AccountResponse accountResponse = new AccountResponse();

        BeanUtils.copyProperties(savedCustomer, customerResponse);
        BeanUtils.copyProperties(savedCustomer.getAccount(), accountResponse);
        BeanUtils.copyProperties(savedCustomer.getAddress(), addressResponse);

        customerResponse.setAccount(accountResponse);
        customerResponse.setAddress(addressResponse);

        return customerResponse;
    }

    @Override
    public Customer getCustomerById(Long id) {
        return this.customerRepository.findById(id).orElseThrow(() ->
                new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString())));
    }

    @Override
    public void delete(long id) {
        customerRepository.delete(getCustomerById(id));
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest request) {
        Customer customer = getCustomerById(id);
        customerMapper.updateFromRequest(request, customer);
        customerRepository.save(customer);
        return customerMapper.toResponse(customer);
    }

    @Override
    public CustomerResponse getById(Long id) {
        return customerMapper.toResponse(getCustomerById(id));
    }

}
