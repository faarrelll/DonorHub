package com.enigma.online_dono_app.service;

import com.enigma.online_dono_app.dto.request.LoginRequest;
import com.enigma.online_dono_app.dto.request.RegisterRequest;
import com.enigma.online_dono_app.dto.response.LoginResponse;
import com.enigma.online_dono_app.dto.response.RegisterResponse;

public interface AuthService {

    RegisterResponse registerAccount(RegisterRequest registerRequest);
    RegisterResponse registerAdmin(RegisterRequest registerRequest, String secretKey);
    LoginResponse login(LoginRequest loginRequest);
}
