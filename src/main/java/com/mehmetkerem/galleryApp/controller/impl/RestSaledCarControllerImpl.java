package com.mehmetkerem.galleryApp.controller.impl;

import com.mehmetkerem.galleryApp.controller.IRestSaledCarController;
import com.mehmetkerem.galleryApp.controller.RestBaseController;
import com.mehmetkerem.galleryApp.controller.RootEntity;
import com.mehmetkerem.galleryApp.dto.request.SaledCarRequest;
import com.mehmetkerem.galleryApp.dto.response.SaledCarResponse;
import com.mehmetkerem.galleryApp.service.ISaledCarService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/saled-car")
public class RestSaledCarControllerImpl extends RestBaseController implements IRestSaledCarController {

    private final ISaledCarService carService;

    public RestSaledCarControllerImpl(ISaledCarService carService) {
        this.carService = carService;
    }

    @PostMapping("/buy")
    @Override
    public RootEntity<SaledCarResponse> buyCar(@RequestBody SaledCarRequest request) {
        return ok(carService.buyCar(request));
    }

    @GetMapping("/{id}")
    @Override
    public RootEntity<SaledCarResponse> getSaledCarById(@PathVariable("id") Long id) {
        return ok(carService.getById(id));
    }

    @PutMapping("{/id}")
    @Override
    public RootEntity<SaledCarResponse> updateSaledCar(@PathVariable("id") Long id, @RequestBody SaledCarRequest request) {
        return ok(carService.update(id, request));
    }

    @DeleteMapping("{/id}")
    @Override
    public void deleteSaledCar(@PathVariable("id") Long id) {
        carService.delete(id);
    }
}
