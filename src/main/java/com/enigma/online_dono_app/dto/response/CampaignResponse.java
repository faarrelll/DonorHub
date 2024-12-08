package com.enigma.online_dono_app.dto.response;

import com.enigma.online_dono_app.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignResponse {
    private String id;
    private String title;
    private String description;
    private Double targetAmount;
    private Double collectedAmount;
    private Status status;
    private String createdBy;
    private LocalDateTime createdAt;
}
