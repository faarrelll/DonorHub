package com.enigma.online_dono_app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonationResponse {
    private String id;
    private String name;
    private String campaignName;
    private Double amountDonation;
    private LocalDateTime Date;
}
