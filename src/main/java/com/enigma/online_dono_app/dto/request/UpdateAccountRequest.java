package com.enigma.online_dono_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountRequest {
//    private String email;
    private String username;
    private String password;
}
