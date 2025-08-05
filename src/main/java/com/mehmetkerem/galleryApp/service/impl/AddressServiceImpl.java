package com.mehmetkerem.galleryApp.service.impl;

import com.mehmetkerem.galleryApp.dto.request.AddressRequest;
import com.mehmetkerem.galleryApp.dto.response.AddressResponse;
import com.mehmetkerem.galleryApp.exception.BaseException;
import com.mehmetkerem.galleryApp.exception.ErrorMessage;
import com.mehmetkerem.galleryApp.exception.MessageType;
import com.mehmetkerem.galleryApp.mapper.AddressMapper;
import com.mehmetkerem.galleryApp.models.Address;
import com.mehmetkerem.galleryApp.repository.AddressRepository;
import com.mehmetkerem.galleryApp.service.IAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressServiceImpl implements IAddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    private Address createAddress(AddressRequest request) {
        Address address = new Address();
        address.setCreateDate(new Date());
        BeanUtils.copyProperties(request, address);
        return address;
    }

    @Override
    public AddressResponse saveAddress(AddressRequest request) {
        AddressResponse addressResponse = new AddressResponse();
        Address savedAddress = addressRepository.save(createAddress(request));
        BeanUtils.copyProperties(savedAddress, addressResponse);
        return addressResponse;
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString())));
    }

    @Override
    public void delete(long id) {
        addressRepository.delete(getAddressById(id));
    }

    @Override
    public AddressResponse update(Long id, AddressRequest request) {
        Address address = getAddressById(id);
        addressMapper.updateFromRequest(request, address);
        addressRepository.save(address);
        return addressMapper.toResponse(address);
    }

    @Override
    public AddressResponse getById(Long id) {
        return addressMapper.toResponse(getAddressById(id));
    }


}
