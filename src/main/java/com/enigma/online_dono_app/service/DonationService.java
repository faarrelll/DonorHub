package com.enigma.online_dono_app.service;

import com.enigma.online_dono_app.dto.request.DonationRequest;
import com.enigma.online_dono_app.dto.request.UpdateStatusDonation;
import com.enigma.online_dono_app.dto.response.DonationResponse;
import com.enigma.online_dono_app.entity.Donation;

public interface DonationService {

    //user dapat donasi ke campaign yang statusnya process
    //uang donasi akan masuk ke campaign jika status donasi sudah paid
    Donation getDonation(String donationId);
    DonationResponse createDonation(String campaign_id, DonationRequest donationRequest);
    DonationResponse updateStatusDonation(String donationId, UpdateStatusDonation updateStatusDonation);
}
