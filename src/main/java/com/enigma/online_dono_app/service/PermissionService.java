package com.enigma.online_dono_app.service;

import com.enigma.online_dono_app.entity.UserAccount;

import java.security.Permission;

public interface PermissionService {
    public boolean hasPermission(String campaign_id, UserAccount userAccount);
}
