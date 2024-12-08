package com.enigma.online_dono_app.service.impl;

import com.enigma.online_dono_app.entity.UserAccount;
import com.enigma.online_dono_app.repository.CampaignRepository;
import com.enigma.online_dono_app.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Permission;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final CampaignRepository campaignRepository;

    @Override
    public boolean hasPermission(String campaign_id, UserAccount userAccount) {
        return campaignRepository.existsByIdAndUserAccount(campaign_id, userAccount);
    }
}
