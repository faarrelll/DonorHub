package com.enigma.online_dono_app.service;


import com.enigma.online_dono_app.dto.request.UpdateAccountRequest;
import com.enigma.online_dono_app.dto.response.LogResponse;
import com.enigma.online_dono_app.dto.response.RegisterResponse;
import com.enigma.online_dono_app.entity.Log;
import com.enigma.online_dono_app.entity.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAccountService extends UserDetailsService {
    UserAccount createUserAccount(UserAccount userAccount);
    boolean findExistEmail(String email);
    Page<RegisterResponse> findAllUserAccounts(int page, int size);
    UserAccount getByContext();
    RegisterResponse updateUserAccount(UpdateAccountRequest updateAccountRequest);
    String deleteUserAccount();
    Log createLog(UserAccount userAccount, String Action);
    void saveLog(UserAccount userAccount);
    Page<LogResponse> findAllLogs(int page, int size);
    Page<LogResponse> findAllLogsByUserId(int page, int size,String userId);
    UserAccount getUserAccountId(String id);
}
