package com.enigma.online_dono_app.dto.request;

import com.enigma.online_dono_app.constant.StatusDonation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusDonation {
    private StatusDonation statusDonation;
}
