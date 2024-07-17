package com.example.bee.service;

import com.example.bee.entity.TaiKhoan;
import com.example.bee.model.dto.JwtAuthenticationResponse;
import com.example.bee.model.dto.RefreshTokenRequest;
import com.example.bee.model.dto.SignUpRequest;
import com.example.bee.model.dto.SigninRequest;

public interface AuthenticationService {
    TaiKhoan signup(SignUpRequest signUpRequest);

    boolean addAdmin();

    JwtAuthenticationResponse signin(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
