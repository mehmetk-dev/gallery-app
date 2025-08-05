package com.mehmetkerem.galleryApp.config;

import com.mehmetkerem.galleryApp.handler.AuthEntryPoint;
import com.mehmetkerem.galleryApp.jwt.JwtAuthenticateFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {

    private final AuthenticationProvider provider;
    private final JwtAuthenticateFilter filter;
    private final AuthEntryPoint authEntryPoint;

    private static final String REGISTER = "/register";
    private static final String AUTHENTICATE = "/authenticate";
    private static final String REFRESH_TOKEN = "/refresh-token";

    public SecurityConfig(AuthenticationProvider provider, JwtAuthenticateFilter filter, AuthEntryPoint authEntryPoint) {
        this.provider = provider;
        this.filter = filter;
        this.authEntryPoint = authEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers(REFRESH_TOKEN, REGISTER, AUTHENTICATE).permitAll()
                                .anyRequest().authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(authEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(provider)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
