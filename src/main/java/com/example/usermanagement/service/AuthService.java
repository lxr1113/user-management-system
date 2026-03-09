package com.example.usermanagement.service;

import com.example.usermanagement.dto.LoginRequest;
import com.example.usermanagement.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    void register(LoginRequest request);
}
