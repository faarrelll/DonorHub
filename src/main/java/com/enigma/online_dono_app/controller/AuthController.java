package com.enigma.online_dono_app.controller;

import com.enigma.online_dono_app.constant.Endpoint;
import com.enigma.online_dono_app.dto.request.LoginRequest;
import com.enigma.online_dono_app.dto.request.RegisterRequest;
import com.enigma.online_dono_app.dto.response.LoginResponse;
import com.enigma.online_dono_app.dto.response.RegisterResponse;
import com.enigma.online_dono_app.service.AuthService;
import com.enigma.online_dono_app.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoint.API_AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register/user")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse registeredUser = authService.registerAccount(registerRequest);
        return ResponseUtils.buildCommonResponse(HttpStatus.CREATED,"Succes Register Account!", registeredUser);
    }

    @PostMapping(path = "/register/admin")
    public ResponseEntity<?> registerAdmin(
            @RequestBody RegisterRequest registerRequest,
            @RequestHeader(name = "X-ADMIN-SECRET-KEY") String secretKey) {
        RegisterResponse registeredUser = authService.registerAdmin(registerRequest,secretKey);
        return ResponseUtils.buildCommonResponse(HttpStatus.CREATED,"Succes Register Account!", registeredUser);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginAccount(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginUser = authService.login(loginRequest);
        return ResponseUtils.buildCommonResponse(HttpStatus.CREATED,"Succes Login Account!", loginUser);
    }
}
