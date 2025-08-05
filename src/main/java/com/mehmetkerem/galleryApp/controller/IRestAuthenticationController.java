package com.mehmetkerem.galleryApp.controller;

import com.mehmetkerem.galleryApp.dto.request.AuthRequest;
import com.mehmetkerem.galleryApp.dto.response.AuthResponse;
import com.mehmetkerem.galleryApp.dto.UserResponse;
import com.mehmetkerem.galleryApp.dto.request.RefreshTokenRequest;

public interface IRestAuthenticationController {

    RootEntity<UserResponse> register(AuthRequest request);
    RootEntity<AuthResponse> authenticate(AuthRequest request);
    RootEntity<AuthResponse> refreshToken(RefreshTokenRequest request);
}
