package com.enigma.online_dono_app.service.impl;

import com.enigma.online_dono_app.constant.Role;
import com.enigma.online_dono_app.dto.request.LoginRequest;
import com.enigma.online_dono_app.dto.request.RegisterRequest;
import com.enigma.online_dono_app.dto.response.LoginResponse;
import com.enigma.online_dono_app.dto.response.RegisterResponse;
import com.enigma.online_dono_app.entity.UserAccount;
import com.enigma.online_dono_app.service.AuthService;
import com.enigma.online_dono_app.service.JwtService;
import com.enigma.online_dono_app.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public RegisterResponse registerAccount(RegisterRequest registerRequest) {
        validateEmail(registerRequest);
        UserAccount newUserAccount = createUserAccount(registerRequest, Role.ROLE_USER);
        return toRegisterResponse(userAccountService.createUserAccount(newUserAccount));
    }


    @Override
    public RegisterResponse registerAdmin(RegisterRequest registerRequest, String secretKey) {
        validateSecretKey(secretKey);
        validateEmail(registerRequest);
        UserAccount newUserAccount = createUserAccount(registerRequest, Role.ROLE_ADMIN);
        return toRegisterResponse(userAccountService.createUserAccount(newUserAccount));
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authenticated = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
        );

        UserAccount userAccount = (UserAccount) authenticated.getPrincipal();

        if (authenticated.isAuthenticated()){
            return LoginResponse.builder()
                    .accessToken(jwtService.generateToken(userAccount))
                    .build();
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
    }

    private UserAccount createUserAccount(RegisterRequest registerRequest, Role role) {
        return  UserAccount.builder()
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(role)
                .build();

    }

    private RegisterResponse toRegisterResponse(UserAccount userAccount) {
        return RegisterResponse.builder()
                .id(userAccount.getId())
                .email(userAccount.getEmail())
                .username(userAccount.getUsername())
                .build();
    }

    private void validateEmail(RegisterRequest registerRequest) {
        if (userAccountService.findExistEmail(registerRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already exist");
        }
    }

    private void validateSecretKey(String secretKey) {
        String secretKeyAdmin = "akuadalahadmin";
        if (!secretKeyAdmin.equals(secretKey)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Invalid secret key");
        }
    }
}
