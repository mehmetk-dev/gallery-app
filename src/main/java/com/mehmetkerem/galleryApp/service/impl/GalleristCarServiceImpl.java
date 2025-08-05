package com.mehmetkerem.galleryApp.service.impl;

import com.mehmetkerem.galleryApp.dto.request.GalleristCarRequest;
import com.mehmetkerem.galleryApp.dto.response.AddressResponse;
import com.mehmetkerem.galleryApp.dto.response.CarResponse;
import com.mehmetkerem.galleryApp.dto.response.GalleristCarResponse;
import com.mehmetkerem.galleryApp.dto.response.GalleristResponse;
import com.mehmetkerem.galleryApp.exception.BaseException;
import com.mehmetkerem.galleryApp.exception.ErrorMessage;
import com.mehmetkerem.galleryApp.exception.MessageType;
import com.mehmetkerem.galleryApp.mapper.GalleristCarMapper;
import com.mehmetkerem.galleryApp.models.Car;
import com.mehmetkerem.galleryApp.models.Gallerist;
import com.mehmetkerem.galleryApp.models.GalleristCar;
import com.mehmetkerem.galleryApp.repository.GalleristCarRepository;
import com.mehmetkerem.galleryApp.service.IGalleristCarService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

    private final GalleristServiceImpl galleristService;
    private final CarServiceImpl carService;
    private final GalleristCarRepository galleristCarRepository;
    private final GalleristCarMapper galleristCarMapper;

    public GalleristCarServiceImpl(GalleristServiceImpl galleristService, CarServiceImpl carService, GalleristCarRepository galleristCarRepository, GalleristCarMapper galleristCarMapper) {
        this.galleristService = galleristService;
        this.carService = carService;
        this.galleristCarRepository = galleristCarRepository;
        this.galleristCarMapper = galleristCarMapper;
    }

    private GalleristCar createGalleristCar(GalleristCarRequest request) {

        Gallerist gallerist = galleristService.getGalleristById(request.getGalleristId());
        Car car = carService.getCarById(request.getCarId());

        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setGallerist(gallerist);
        galleristCar.setCar(car);
        galleristCar.setCreateDate(new Date());

        return galleristCar;
    }


    @Override
    public GalleristCarResponse saveGalleristCar(GalleristCarRequest request) {
        GalleristCar galleristCar = galleristCarRepository.save(createGalleristCar(request));

        GalleristCarResponse galleristCarResponse = new GalleristCarResponse();
        CarResponse carResponse = new CarResponse();
        GalleristResponse galleristResponse = new GalleristResponse();
        AddressResponse addressResponse = new AddressResponse();

        BeanUtils.copyProperties(galleristCar.getCar(), carResponse);
        BeanUtils.copyProperties(galleristCar.getGallerist(), galleristResponse);
        BeanUtils.copyProperties(galleristCar.getGallerist().getAddress(), addressResponse);
        BeanUtils.copyProperties(galleristCar, galleristCarResponse);

        galleristResponse.setAddress(addressResponse);
        galleristCarResponse.setCarResponse(carResponse);
        galleristCarResponse.setGalleristResponse(galleristResponse);

        return galleristCarResponse;
    }

    private GalleristCar getGalleristCarById(Long id) {
        return galleristCarRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString())));
    }

    @Override
    public void delete(long id) {
        galleristCarRepository.delete(getGalleristCarById(id));
    }

    @Override
    public GalleristCarResponse update(Long id, GalleristCarRequest request) {
        GalleristCar galleristCar = getGalleristCarById(id);
        galleristCarMapper.updateFromRequest(request,galleristCar);
        galleristCarRepository.save(galleristCar);
        return galleristCarMapper.toResponse(galleristCar);
    }

    @Override
    public GalleristCarResponse getById(Long id) {
        return galleristCarMapper.toResponse(getGalleristCarById(id));
    }

}
