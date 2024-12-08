package com.enigma.online_dono_app.service;

import com.enigma.online_dono_app.entity.UserAccount;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(UserAccount userAccount);

    String extractUsername(String jwtToken);

    boolean validateToken(String jwtToken, UserDetails userDetails);
}
