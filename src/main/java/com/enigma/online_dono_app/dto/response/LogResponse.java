package com.enigma.online_dono_app.dto.response;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogResponse {
    private String id;
    private String username;
    private String Action;
    private LocalDateTime DateTime;
}
