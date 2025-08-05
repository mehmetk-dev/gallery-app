package com.mehmetkerem.galleryApp.service;

import com.mehmetkerem.galleryApp.dto.request.AuthRequest;
import com.mehmetkerem.galleryApp.dto.response.AuthResponse;
import com.mehmetkerem.galleryApp.dto.UserResponse;
import com.mehmetkerem.galleryApp.dto.request.RefreshTokenRequest;

public interface IAuthenticationService {

    UserResponse register(AuthRequest request);
    AuthResponse authenticate(AuthRequest request);
    AuthResponse refreshToken(RefreshTokenRequest request);
}
