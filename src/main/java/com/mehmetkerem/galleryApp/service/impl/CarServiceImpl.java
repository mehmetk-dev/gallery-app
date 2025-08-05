package com.mehmetkerem.galleryApp.service.impl;

import com.mehmetkerem.galleryApp.dto.request.CarRequest;
import com.mehmetkerem.galleryApp.dto.response.CarResponse;
import com.mehmetkerem.galleryApp.exception.BaseException;
import com.mehmetkerem.galleryApp.exception.ErrorMessage;
import com.mehmetkerem.galleryApp.exception.MessageType;
import com.mehmetkerem.galleryApp.mapper.CarMapper;
import com.mehmetkerem.galleryApp.models.Car;
import com.mehmetkerem.galleryApp.repository.CarRepository;
import com.mehmetkerem.galleryApp.service.ICarService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CarServiceImpl implements ICarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    private Car createCar(CarRequest request) {
        Car car = new Car();
        BeanUtils.copyProperties(request, car);
        car.setCreateDate(new Date());
        return car;
    }

    @Override
    public CarResponse saveCar(CarRequest request) {
        Car savedCar = carRepository.save(createCar(request));
        CarResponse carResponse = new CarResponse();
        BeanUtils.copyProperties(savedCar, carResponse);
        return carResponse;
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString())));
    }

    @Override
    public void delete(long id) {
        carRepository.delete(getCarById(id));
    }

    @Override
    public CarResponse update(Long id, CarRequest request) {
        Car car = getCarById(id);
        carMapper.updateFromRequest(request, car);
        carRepository.save(car);
        return carMapper.toResponse(car);
    }

    @Override
    public CarResponse getById(Long id) {
        return carMapper.toResponse(getCarById(id));
    }

}
