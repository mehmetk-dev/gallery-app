package com.mehmetkerem.galleryApp.controller.impl;

import com.mehmetkerem.galleryApp.controller.IRestCarController;
import com.mehmetkerem.galleryApp.controller.RestBaseController;
import com.mehmetkerem.galleryApp.controller.RootEntity;
import com.mehmetkerem.galleryApp.dto.request.CarRequest;
import com.mehmetkerem.galleryApp.dto.response.CarResponse;
import com.mehmetkerem.galleryApp.service.ICarService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController {

    private final ICarService carService;

    public RestCarControllerImpl(ICarService carService) {
        this.carService = carService;
    }

    @PostMapping("/save")
    @Override
    public RootEntity<CarResponse> saveCar(@RequestBody CarRequest request) {
        return ok(carService.saveCar(request));
    }

    @GetMapping("/{id}")
    @Override
    public RootEntity<CarResponse> getCarById(@PathVariable("id") Long id) {
        return ok(carService.getById(id));
    }

    @PutMapping("/{id}")
    @Override
    public RootEntity<CarResponse> updateCar(@PathVariable("id") Long id, @RequestBody CarRequest request) {
        return ok(carService.update(id, request));
    }

    @DeleteMapping("{/id}")
    @Override
    public void deleteCar(@PathVariable("id") Long id) {
        carService.delete(id);
    }
}
