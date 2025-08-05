package com.mehmetkerem.galleryApp.controller.impl;

import com.mehmetkerem.galleryApp.controller.IRestGalleristCarController;
import com.mehmetkerem.galleryApp.controller.RestBaseController;
import com.mehmetkerem.galleryApp.controller.RootEntity;
import com.mehmetkerem.galleryApp.dto.request.GalleristCarRequest;
import com.mehmetkerem.galleryApp.dto.response.GalleristCarResponse;
import com.mehmetkerem.galleryApp.service.IGalleristCarService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/gallerist-car")
public class RestGalleristCarController extends RestBaseController implements IRestGalleristCarController {

    private final IGalleristCarService galleristCarService;

    public RestGalleristCarController(IGalleristCarService galleristCarService) {
        this.galleristCarService = galleristCarService;
    }

    @PostMapping("/save")
    @Override
    public RootEntity<GalleristCarResponse> saveGalleristCar(@RequestBody GalleristCarRequest request) {
        return ok(galleristCarService.saveGalleristCar(request));
    }

    @GetMapping("/{id}")
    @Override
    public RootEntity<GalleristCarResponse> getGalleristCarById(@PathVariable("id")Long id) {
        return ok(galleristCarService.getById(id));
    }

    @PutMapping("/{id}")
    @Override
    public RootEntity<GalleristCarResponse> updateGalleristCar(@PathVariable("id")Long id,@RequestBody GalleristCarRequest request) {
        return ok(galleristCarService.update(id,request));
    }

    @DeleteMapping("{/id}")
    @Override
    public void deleteGalleristCar(@PathVariable("id")Long id) {
        galleristCarService.delete(id);
    }
}
