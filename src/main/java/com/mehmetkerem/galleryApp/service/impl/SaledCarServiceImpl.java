package com.mehmetkerem.galleryApp.service.impl;

import com.mehmetkerem.galleryApp.dto.request.SaledCarRequest;
import com.mehmetkerem.galleryApp.dto.response.*;
import com.mehmetkerem.galleryApp.enums.CarStatusType;
import com.mehmetkerem.galleryApp.enums.CurrencyType;
import com.mehmetkerem.galleryApp.exception.BaseException;
import com.mehmetkerem.galleryApp.exception.ErrorMessage;
import com.mehmetkerem.galleryApp.exception.MessageType;
import com.mehmetkerem.galleryApp.mapper.SaledCarMapper;
import com.mehmetkerem.galleryApp.models.Car;
import com.mehmetkerem.galleryApp.models.Customer;
import com.mehmetkerem.galleryApp.models.SaledCar;
import com.mehmetkerem.galleryApp.repository.CarRepository;
import com.mehmetkerem.galleryApp.repository.CustomerRepository;
import com.mehmetkerem.galleryApp.repository.SaledCarRepository;
import com.mehmetkerem.galleryApp.service.ISaledCarService;
import com.mehmetkerem.galleryApp.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Service
public class SaledCarServiceImpl implements ISaledCarService {


    private final SaledCarRepository saledCarRepository;
    private final CustomerRepository customerRepository;
    private final CarServiceImpl carService;
    private final CarRepository carRepository;
    private final GalleristServiceImpl galleristService;
    private final CustomerServiceImpl customerService;
    private final CurrencyRatesServiceImpl currencyRatesService;
    private final SaledCarMapper saledCarMapper;

    public SaledCarServiceImpl(SaledCarRepository saledCarRepository,
                               CustomerRepository customerRepository,
                               CarServiceImpl carService,
                               CarRepository carRepository,
                               GalleristServiceImpl galleristService,
                               CustomerServiceImpl customerService,
                               CurrencyRatesServiceImpl currencyRatesService,
                               SaledCarMapper saledCarMapper) {
        this.saledCarRepository = saledCarRepository;
        this.customerRepository = customerRepository;
        this.carService = carService;
        this.carRepository = carRepository;
        this.galleristService = galleristService;
        this.customerService = customerService;
        this.currencyRatesService = currencyRatesService;
        this.saledCarMapper = saledCarMapper;
    }

    public boolean isCarSalable(Car car) {
        return car.getCarStatusType().equals(CarStatusType.SALABLE);
    }

    public BigDecimal getUSDValue() {
        String currencyDate = DateUtils.getCurrencyDate(new Date());
        CurrencyRatesResponse currency = currencyRatesService.getCurrency(currencyDate, currencyDate);
        return new BigDecimal(currency.getItems().getFirst().getUsd());
    }

    public BigDecimal convertTLToUSD(BigDecimal amount) {
        return amount.divide(getUSDValue(), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal checkAmount(SaledCarRequest request) {
        Car car = carService.getCarById(request.getCarId());
        BigDecimal carAmount = car.getCurrencyType().equals(CurrencyType.TL)
                ? convertTLToUSD(car.getPrice())
                : car.getPrice();

        BigDecimal customerAmount = convertTLToUSD(customerService.getCustomerById(request.getCustomerId()).getAccount().getAmount());

        if (customerAmount.compareTo(carAmount) >= 0) {
            return customerAmount.subtract(carAmount);
        } else {
            return BigDecimal.valueOf(-1);
        }
    }

    private SaledCar createSaledCar(SaledCarRequest request) {
        SaledCar saledCar = new SaledCar();
        saledCar.setCreateDate(new Date());
        saledCar.setCar(carService.getCarById(request.getCarId()));
        saledCar.setCustomer(customerService.getCustomerById(request.getCustomerId()));
        saledCar.setGallerist(galleristService.getGalleristById(request.getGalleristId()));

        return saledCar;
    }

    @Transactional
    @Override
    public SaledCarResponse buyCar(SaledCarRequest request) {
        BigDecimal result = checkAmount(request);
        if (result.compareTo(BigDecimal.ZERO) < 0) {
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, null));
        }

        Car car = carRepository.getReferenceById(request.getCarId());
        if (!isCarSalable(car)) {
            throw new BaseException(new ErrorMessage(MessageType.CAR_IS_SALED, car.getId().toString()));
        }
        SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(request));

        car.setCarStatusType(CarStatusType.SALED);
        carRepository.save(car);

        Customer customer = savedSaledCar.getCustomer();
        customer.getAccount().setAmount(result.multiply(getUSDValue()));
        customerRepository.save(customer);

        return toResponse(savedSaledCar);
    }

    public SaledCarResponse toResponse(SaledCar saledCar) {
        SaledCarResponse saledCarResponse = new SaledCarResponse();
        CustomerResponse customerResponse = new CustomerResponse();
        CarResponse carResponse = new CarResponse();
        GalleristResponse galleristResponse = new GalleristResponse();
        AddressResponse customerAddressResponse = new AddressResponse();
        AddressResponse galleristAddressResponse = new AddressResponse();
        AccountResponse accountResponse = new AccountResponse();

        BeanUtils.copyProperties(saledCar, saledCarResponse);
        BeanUtils.copyProperties(saledCar.getCar(), carResponse);
        BeanUtils.copyProperties(saledCar.getCustomer(), customerResponse);
        BeanUtils.copyProperties(saledCar.getGallerist(), galleristResponse);
        BeanUtils.copyProperties(saledCar.getGallerist().getAddress(), galleristAddressResponse);
        BeanUtils.copyProperties(saledCar.getCustomer().getAddress(), customerAddressResponse);
        BeanUtils.copyProperties(saledCar.getCustomer().getAccount(), accountResponse);

        customerResponse.setAddress(customerAddressResponse);
        customerResponse.setAccount(accountResponse);
        galleristResponse.setAddress(galleristAddressResponse);
        saledCarResponse.setCar(carResponse);
        saledCarResponse.setCustomer(customerResponse);
        saledCarResponse.setGallerist(galleristResponse);

        return saledCarResponse;
    }

    private SaledCar getSaledCarById(Long id) {
        return saledCarRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString())));
    }

    @Transactional
    @Override
    public void delete(long id) {
        SaledCar saledCar = getSaledCarById(id);
        saledCarRepository.delete(saledCar);
        Car car = saledCar.getCar();
        car.setCarStatusType(CarStatusType.SALABLE);
        carRepository.save(car);
    }

    @Override
    public SaledCarResponse update(Long id, SaledCarRequest request) {
        SaledCar saledCar = getSaledCarById(id);
        saledCarMapper.updateFromRequest(request,saledCar);
        return toResponse(saledCarRepository.save(saledCar));
    }

    @Override
    public SaledCarResponse getById(Long id) {
        return saledCarMapper.toResponse(getSaledCarById(id));
    }

}
