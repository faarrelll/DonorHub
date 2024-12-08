package com.enigma.online_dono_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignRequest {
    private String title;
    private String description;
    private Double targetAmount;

}
