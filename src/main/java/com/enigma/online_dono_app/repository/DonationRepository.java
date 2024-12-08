package com.enigma.online_dono_app.repository;

import com.enigma.online_dono_app.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, String> {
}
