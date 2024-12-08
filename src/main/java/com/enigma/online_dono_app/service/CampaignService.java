package com.enigma.online_dono_app.service;

import com.enigma.online_dono_app.dto.request.CampaignRequest;
import com.enigma.online_dono_app.dto.request.UpdateCampaignRequest;
import com.enigma.online_dono_app.dto.request.UpdateStatusCampaign;
import com.enigma.online_dono_app.dto.response.CampaignResponse;
import com.enigma.online_dono_app.entity.Campaign;
import org.springframework.data.domain.Page;

public interface CampaignService {
    //user create campaign bisa admin bisa user
    CampaignResponse createCampaign(CampaignRequest campaignRequest);
    // edit campaign by id, bisa admin atau user yang berhubungan
    CampaignResponse updateCampaign(String id, UpdateCampaignRequest updateCampaignRequest);
    // edit status campagin // approve, reject, complete
    CampaignResponse updateStatusCampaign(String id,UpdateStatusCampaign updateStatusCampaign);
    // delete campagin, admin dan user yang membuat
    String deleteCampaign(String id);
    // get all campagaign semua campagin yang ada
    Page<CampaignResponse> getAllCampaigns(int page, int size);
    // get all campagaign punya user
//    Page<CampaignResponse> getAllUserCampaigns(int page, int size, String Status);
    // get campaign by id -> hanya admin
    CampaignResponse getCampaign(String id);
    Campaign getCampaignById(String id);
    void saveCampaign(Campaign campaign);
}
