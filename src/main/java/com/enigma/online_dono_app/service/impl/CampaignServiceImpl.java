package com.enigma.online_dono_app.service.impl;

import com.enigma.online_dono_app.constant.Role;
import com.enigma.online_dono_app.constant.Status;
import com.enigma.online_dono_app.dto.request.CampaignRequest;
import com.enigma.online_dono_app.dto.request.UpdateCampaignRequest;
import com.enigma.online_dono_app.dto.request.UpdateStatusCampaign;
import com.enigma.online_dono_app.dto.response.CampaignResponse;
import com.enigma.online_dono_app.entity.Campaign;
import com.enigma.online_dono_app.entity.UserAccount;
import com.enigma.online_dono_app.repository.CampaignRepository;
import com.enigma.online_dono_app.service.CampaignService;
import com.enigma.online_dono_app.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;
    private final UserAccountService userAccountService;

    @Override
    public CampaignResponse createCampaign(CampaignRequest campaignRequest) {
        Campaign newCampaign = Campaign.builder()
                .title(campaignRequest.getTitle())
                .description(campaignRequest.getDescription())
                .targetAmount(campaignRequest.getTargetAmount())
                .collectedAmount(0d)
                .status(Status.PENDING)
                .userAccount(userAccountService.getByContext())
                .build();
        campaignRepository.saveAndFlush(newCampaign);
        return toCampaignResponse(newCampaign);
    }

    @Override
    public CampaignResponse updateCampaign(String id,UpdateCampaignRequest updateCampaignRequest) {
        Campaign campaign = getCampaignById(id);
        campaign.setTitle(updateCampaignRequest.getTitle());
        campaign.setDescription(updateCampaignRequest.getDescription());
        campaign.setTargetAmount(updateCampaignRequest.getTargetAmount());
        campaignRepository.saveAndFlush(campaign);
        return toCampaignResponse(campaign);
    }

    @Override
    public CampaignResponse updateStatusCampaign(String id, UpdateStatusCampaign updateStatusCampaign) {
        Campaign campaign = getCampaignById(id);
        campaign.setStatus(updateStatusCampaign.getStatus());
        campaignRepository.saveAndFlush(campaign);
        return toCampaignResponse(campaign);
    }

    @Override
    public String deleteCampaign(String campaignId) {
        Campaign campaign = getCampaignById(campaignId);
        String deletedCampaign = campaign.getTitle();
        campaignRepository.delete(campaign);
        return deletedCampaign;
    }

    @Override
    public Page<CampaignResponse> getAllCampaigns(int page, int size) {
        UserAccount userAccount = userAccountService.getByContext();
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Campaign> campaigns;
        if (userAccount.getRole().equals(Role.ROLE_USER)) {
            campaigns = campaignRepository.findByStatus(Status.PROCESS, pageable);
        } else {
            campaigns = campaignRepository.findAll(pageable);
        }
        return campaigns.map(this::toCampaignResponse);
    }

//    @Override
//    public Page<CampaignResponse> getAllUserCampaigns(int page, int size, String status) {
//        return null;
//    }

    @Override
    public CampaignResponse getCampaign(String campaignId) {
        return toCampaignResponse(getCampaignById(campaignId));
    }

    @Override
    public Campaign getCampaignById(String id) {
        return campaignRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Campaign not found"));

    }

    @Override
    public void saveCampaign(Campaign campaign) {
        campaignRepository.save(campaign);
    }

    public CampaignResponse toCampaignResponse(Campaign campaign) {
        return CampaignResponse.builder()
                .id(campaign.getId())
                .title(campaign.getTitle())
                .description(campaign.getDescription())
                .targetAmount(campaign.getTargetAmount())
                .collectedAmount(campaign.getCollectedAmount())
                .status(campaign.getStatus())
                .createdBy(campaign.getUserAccount().getUsername())
                .createdAt(campaign.getCreatedAt())
                .build();
    }
}
