package com.enigma.online_dono_app.repository;

import com.enigma.online_dono_app.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    UserDetails findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
