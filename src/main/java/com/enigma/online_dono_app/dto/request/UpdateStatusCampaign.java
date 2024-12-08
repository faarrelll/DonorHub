package com.enigma.online_dono_app.dto.request;

import com.enigma.online_dono_app.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusCampaign {
    private Status status;
}
