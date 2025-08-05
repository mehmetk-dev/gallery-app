package com.mehmetkerem.galleryApp.controller.impl;

import com.mehmetkerem.galleryApp.controller.IRestGalleristController;
import com.mehmetkerem.galleryApp.controller.RestBaseController;
import com.mehmetkerem.galleryApp.controller.RootEntity;
import com.mehmetkerem.galleryApp.dto.request.GalleristRequest;
import com.mehmetkerem.galleryApp.dto.response.GalleristResponse;
import com.mehmetkerem.galleryApp.service.IGalleristService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/gallerist")
public class RestGalleristControllerImpl extends RestBaseController implements IRestGalleristController {

    private final IGalleristService galleristService;

    public RestGalleristControllerImpl(IGalleristService galleristService) {
        this.galleristService = galleristService;
    }

    @PostMapping("/save")
    @Override
    public RootEntity<GalleristResponse> saveGallerist(@Valid @RequestBody GalleristRequest request) {
        return ok(galleristService.saveGallerist(request));
    }

    @GetMapping("/{id}")
    @Override
    public RootEntity<GalleristResponse> getGalleristById(@PathVariable("id")Long id) {
        return ok(galleristService.getById(id));
    }

    @PutMapping("{/id}")
    @Override
    public RootEntity<GalleristResponse> updateGallerist(@PathVariable("id")Long id,@RequestBody GalleristRequest request) {
        return ok(galleristService.update(id, request));
    }

    @DeleteMapping("{/id}")
    @Override
    public void deleteGallerist(@PathVariable("id")Long id) {
        galleristService.delete(id);
    }
}
