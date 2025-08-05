package com.mehmetkerem.galleryApp.controller.impl;

import com.mehmetkerem.galleryApp.controller.IRestAuthenticationController;
import com.mehmetkerem.galleryApp.controller.RestBaseController;
import com.mehmetkerem.galleryApp.controller.RootEntity;
import com.mehmetkerem.galleryApp.dto.request.AuthRequest;
import com.mehmetkerem.galleryApp.dto.response.AuthResponse;
import com.mehmetkerem.galleryApp.dto.UserResponse;
import com.mehmetkerem.galleryApp.dto.request.RefreshTokenRequest;
import com.mehmetkerem.galleryApp.service.IAuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

    private final IAuthenticationService authenticationService;

    public RestAuthenticationControllerImpl(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    @Override
    public RootEntity<UserResponse> register(@RequestBody AuthRequest request) {
        return RootEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    @Override
    public RootEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return RootEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    @Override
    public RootEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return RootEntity.ok(authenticationService.refreshToken(request));
    }
}
