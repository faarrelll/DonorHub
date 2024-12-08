package com.enigma.online_dono_app.service.impl;

import com.enigma.online_dono_app.dto.request.RegisterRequest;
import com.enigma.online_dono_app.dto.request.UpdateAccountRequest;
import com.enigma.online_dono_app.dto.response.RegisterResponse;
import com.enigma.online_dono_app.entity.Log;
import com.enigma.online_dono_app.entity.UserAccount;
import com.enigma.online_dono_app.repository.UserAccountRepository;
import com.enigma.online_dono_app.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Override
    public UserAccount createUserAccount(UserAccount userAccount) {
        if (userAccount.getLogs() == null) {
            userAccount.setLogs(new ArrayList<>());
        }
        userAccount.getLogs().add(createLog(userAccount, "Registered to Apps"));
        return userAccountRepository.saveAndFlush(userAccount);
    }

    @Override
    public boolean findExistEmail(String email) {
        return userAccountRepository.existsByEmail(email);
    }

    @Override
    public Page<RegisterResponse> findAllUserAccounts(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<UserAccount> userAccounts = userAccountRepository.findAll(pageable);
        return userAccounts.map(this::toRegisterResponse);
    }

    @Override
    public UserAccount getByContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserAccount) authentication.getPrincipal();
    }

    @Override
    public RegisterResponse updateUserAccount(UpdateAccountRequest updateAccountRequest) {
        UserAccount updatedUserAccount = getByContext();
        if (findExistUsername(updateAccountRequest.getUsername())) {
            throw new UsernameNotFoundException("Username already exists");
        }
//        updatedUserAccount.setEmail(updateAccountRequest.getEmail());
        updatedUserAccount.setUsername(updateAccountRequest.getUsername());
        updatedUserAccount.setPassword(updateAccountRequest.getPassword());
        updatedUserAccount.getLogs().add(createLog(updatedUserAccount, "Updated user account"));
        userAccountRepository.saveAndFlush(updatedUserAccount);
        return toRegisterResponse(updatedUserAccount);
    }

    @Override
    public String deleteUserAccount() {
        UserAccount userAccount = getByContext();
        String emailDeletedUser = userAccount.getUsername();
        userAccountRepository.delete(userAccount);
        return emailDeletedUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAccountRepository.findByEmail(username);
    }

    public RegisterResponse toRegisterResponse(UserAccount userAccount) {
        return RegisterResponse.builder()
                .id(userAccount.getId())
                .email(userAccount.getUsername())
                .username(userAccount.getEmail())
                .build();
    }

    public boolean findExistUsername(String username) {
        return userAccountRepository.existsByUsername(username);
    }

    @Override
    public Log createLog(UserAccount userAccount, String Action) {
        return Log.builder()
                .userAccount(userAccount)
                .Action(Action)
                .build();
    }

    @Override
    public void saveLog(UserAccount userAccount) {
        userAccountRepository.saveAndFlush(userAccount);
    }
}
