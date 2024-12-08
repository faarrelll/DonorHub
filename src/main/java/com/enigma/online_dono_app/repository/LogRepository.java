package com.enigma.online_dono_app.repository;

import com.enigma.online_dono_app.entity.Log;
import com.enigma.online_dono_app.entity.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, String> {
    Page<Log> findAllByUserAccount(UserAccount userAccount, Pageable pageable);
}
