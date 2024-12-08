package com.enigma.online_dono_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String Action;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @CreationTimestamp
    private LocalDateTime createdAt;



}
