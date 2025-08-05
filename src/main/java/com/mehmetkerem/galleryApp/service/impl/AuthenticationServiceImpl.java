package com.mehmetkerem.galleryApp.service.impl;

import com.mehmetkerem.galleryApp.dto.request.AuthRequest;
import com.mehmetkerem.galleryApp.dto.response.AuthResponse;
import com.mehmetkerem.galleryApp.dto.UserResponse;
import com.mehmetkerem.galleryApp.dto.request.RefreshTokenRequest;
import com.mehmetkerem.galleryApp.exception.BaseException;
import com.mehmetkerem.galleryApp.exception.ErrorMessage;
import com.mehmetkerem.galleryApp.exception.MessageType;
import com.mehmetkerem.galleryApp.jwt.JwtService;
import com.mehmetkerem.galleryApp.models.RefreshToken;
import com.mehmetkerem.galleryApp.models.User;
import com.mehmetkerem.galleryApp.repository.RefreshTokenRepository;
import com.mehmetkerem.galleryApp.repository.UserRepository;
import com.mehmetkerem.galleryApp.service.IAuthenticationService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final UserRepository repository;
    private final AuthenticationProvider provider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtService jwtService;


    public AuthenticationServiceImpl(UserRepository repository,
                                     BCryptPasswordEncoder encoder,
                                     RefreshTokenRepository refreshTokenRepository,
                                     JwtService jwtService,
                                     AuthenticationProvider provider) {
        this.repository = repository;
        this.encoder = encoder;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtService = jwtService;
        this.provider = provider;
    }

    @Transactional
    private User createUser(AuthRequest request) {
        if (repository.findByUsername(request.getUsername()).isPresent()) {
            throw new BaseException(new ErrorMessage(MessageType.USER_ALL_READY_SAVED, request.getUsername()));
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setCreateDate(new Date());
        user.setPassword(encoder.encode(request.getPassword()));

        return user;
    }

    private RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreateDate(new Date());
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
        refreshToken.setUser(user);
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        return refreshToken;
    }

    @Override
    public UserResponse register(AuthRequest request) {
        User savedUser = repository.save(createUser(request));

        UserResponse dtoUser = new UserResponse();
        BeanUtils.copyProperties(savedUser, dtoUser);

        return dtoUser;
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            provider.authenticate(authenticationToken);

            Optional<User> optUser = repository.findByUsername(request.getUsername());

            String accessToken = jwtService.generateToken(optUser.get());
            RefreshToken refreshToken = refreshTokenRepository.save(createRefreshToken(optUser.get()));

            return new AuthResponse(accessToken, refreshToken.getRefreshToken());

        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
        }
    }

    public boolean isRefreshTokenExpire(Date expiredDate) {
        return new Date().before(expiredDate);
    }

    @Transactional
    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        Optional<RefreshToken> byRefreshToken = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());

        if (byRefreshToken.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, request.getRefreshToken()));
        }

        if (!isRefreshTokenExpire(byRefreshToken.get().getExpiredDate())) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, byRefreshToken.get().getExpiredDate().toString()));
        }

        String newAccessToken = jwtService.generateToken(byRefreshToken.get().getUser());
        RefreshToken newRefreshToken = refreshTokenRepository.save(createRefreshToken(byRefreshToken.get().getUser()));

        return new AuthResponse(newAccessToken, newRefreshToken.getRefreshToken());
    }
}
