package com.enigma.online_dono_app.repository;

import com.enigma.online_dono_app.constant.Status;
import com.enigma.online_dono_app.entity.Campaign;
import com.enigma.online_dono_app.entity.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, String> {
    Page<Campaign> findByUserAccount(UserAccount userAccount, Pageable pageable);

    boolean existsByIdAndUserAccount(String id, UserAccount userAccount);

    Page<Campaign> findByStatus(Status status, Pageable pageable);
}
