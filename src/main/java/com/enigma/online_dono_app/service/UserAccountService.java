package com.enigma.online_dono_app.service;


import com.enigma.online_dono_app.dto.request.RegisterRequest;
import com.enigma.online_dono_app.dto.request.UpdateAccountRequest;
import com.enigma.online_dono_app.dto.response.RegisterResponse;
import com.enigma.online_dono_app.entity.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserAccountService extends UserDetailsService {
    UserAccount createUserAccount(UserAccount userAccount);
    boolean findExistEmail(String email);
    Page<RegisterResponse> findAllUserAccounts(int page, int size);
    UserAccount getByContext();
    RegisterResponse updateUserAccount(UpdateAccountRequest updateAccountRequest);
    String deleteUserAccount();
}
