package com.enigma.online_dono_app.service.impl;

import com.enigma.online_dono_app.entity.UserAccount;
import com.enigma.online_dono_app.repository.UserAccountRepository;
import com.enigma.online_dono_app.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Override
    public UserAccount createUserAccount(UserAccount userAccount) {
        return userAccountRepository.saveAndFlush(userAccount);
    }

    @Override
    public boolean findExistEmail(String email) {
        return userAccountRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAccountRepository.findByEmail(username);
    }
}
