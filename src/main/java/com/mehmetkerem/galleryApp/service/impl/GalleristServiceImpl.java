package com.mehmetkerem.galleryApp.service.impl;

import com.mehmetkerem.galleryApp.dto.request.GalleristRequest;
import com.mehmetkerem.galleryApp.dto.response.AddressResponse;
import com.mehmetkerem.galleryApp.dto.response.GalleristResponse;
import com.mehmetkerem.galleryApp.exception.BaseException;
import com.mehmetkerem.galleryApp.exception.ErrorMessage;
import com.mehmetkerem.galleryApp.exception.MessageType;
import com.mehmetkerem.galleryApp.mapper.GalleristMapper;
import com.mehmetkerem.galleryApp.models.Address;
import com.mehmetkerem.galleryApp.models.Gallerist;
import com.mehmetkerem.galleryApp.repository.GalleristRepository;
import com.mehmetkerem.galleryApp.service.IGalleristService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class GalleristServiceImpl implements IGalleristService {

    private final GalleristRepository galleristRepository;
    private final AddressServiceImpl addressService;
    private final GalleristMapper mapper;

    public GalleristServiceImpl(GalleristRepository galleristRepository, AddressServiceImpl addressService, GalleristMapper mapper) {
        this.galleristRepository = galleristRepository;
        this.addressService = addressService;
        this.mapper = mapper;
    }

    @Transactional
    private Gallerist createGallerist(GalleristRequest request) {
        Address address = addressService.getAddressById(request.getAddressId());
        if (galleristRepository.existsByAddress_Id(request.getAddressId())) {
            throw new BaseException(new ErrorMessage(MessageType.ADDRESS_ALL_READY_SAVED, request.getAddressId().toString()));
        }
        Gallerist gallerist = new Gallerist();
        BeanUtils.copyProperties(request, gallerist);
        gallerist.setAddress(address);
        gallerist.setCreateDate(new Date());
        return gallerist;
    }

    @Override
    public GalleristResponse saveGallerist(GalleristRequest request) {
        Gallerist savedGallerist = galleristRepository.save(createGallerist(request));
        GalleristResponse galleristResponse = new GalleristResponse();
        AddressResponse addressResponse = new AddressResponse();
        BeanUtils.copyProperties(savedGallerist.getAddress(), addressResponse);
        BeanUtils.copyProperties(savedGallerist, galleristResponse);
        galleristResponse.setAddress(addressResponse);

        return galleristResponse;
    }

    public Gallerist getGalleristById(Long id){
        return galleristRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString())));
    }

    @Override
    public void delete(long id) {
        galleristRepository.delete(getGalleristById(id));
    }

    @Override
    public GalleristResponse update(Long id, GalleristRequest request) {
        Gallerist gallerist = getGalleristById(id);
        mapper.updateFromRequest(request,gallerist);
        return mapper.toResponse(galleristRepository.save(gallerist));
    }

    @Override
    public GalleristResponse getById(Long id) {
        return mapper.toResponse(getGalleristById(id));
    }


}
