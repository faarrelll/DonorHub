package com.enigma.online_dono_app.service.impl;

import com.enigma.online_dono_app.constant.Status;
import com.enigma.online_dono_app.constant.StatusDonation;
import com.enigma.online_dono_app.dto.request.DonationRequest;
import com.enigma.online_dono_app.dto.request.UpdateStatusDonation;
import com.enigma.online_dono_app.dto.response.DonationResponse;
import com.enigma.online_dono_app.entity.Campaign;
import com.enigma.online_dono_app.entity.Donation;
import com.enigma.online_dono_app.repository.DonationRepository;
import com.enigma.online_dono_app.service.CampaignService;
import com.enigma.online_dono_app.service.DonationService;
import com.enigma.online_dono_app.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {
    private final DonationRepository donationRepository;
    private final UserAccountService userAccountService;
    private final CampaignService campaignService;

    @Override
    public Donation getDonation(String donationId) {
        return donationRepository.findById(donationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Donation not found"));
    }

    @Override
    public DonationResponse createDonation(String campaign_id, DonationRequest donationRequest) {
        Campaign campaign = campaignService.getCampaignById(campaign_id);
        if (campaign.getStatus().equals(Status.PROCESS)) {
            Donation donation = Donation.builder()
                    .amount(donationRequest.getAmount())
                    .userAccount(userAccountService.getByContext())
                    .statusDonation(StatusDonation.PENDING)
                    .campaign(campaign)
                    .build();

            donationRepository.saveAndFlush(donation);
            return toDonationResponse(donation);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Campaign is Closed");
    }

    @Override
    public DonationResponse updateStatusDonation(String donationId, UpdateStatusDonation updateStatusDonation) {
        Donation donation = getDonation(donationId);
        Campaign campaign = donation.getCampaign();
        donation.setStatusDonation(updateStatusDonation.getStatusDonation());
        if (donation.getStatusDonation().equals(StatusDonation.PAID)) {
            campaign.setCollectedAmount(campaign.getCollectedAmount()+donation.getAmount());
        }
        donationRepository.saveAndFlush(donation);
        campaignService.saveCampaign(campaign);

        return toDonationResponse(donation);
    }

    public DonationResponse toDonationResponse(Donation donation){
        return DonationResponse.builder()
                .id(donation.getId())
                .name(donation.getUserAccount().getUsername())
                .campaignName(donation.getCampaign().getTitle())
                .amountDonation(donation.getAmount())
                .Date(donation.getCreatedAt())
                .build();
    }
}
