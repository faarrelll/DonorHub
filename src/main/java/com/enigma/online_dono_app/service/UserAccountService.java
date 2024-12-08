package com.enigma.online_dono_app.service;

import com.enigma.online_dono_app.entity.UserAccount;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAccountService extends UserDetailsService {
    UserAccount createUserAccount(UserAccount userAccount);
    boolean findExistEmail(String email);
}
