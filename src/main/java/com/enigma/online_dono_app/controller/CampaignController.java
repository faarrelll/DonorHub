package com.enigma.online_dono_app.controller;

import com.enigma.online_dono_app.constant.Endpoint;
import com.enigma.online_dono_app.dto.request.CampaignRequest;
import com.enigma.online_dono_app.dto.request.UpdateCampaignRequest;
import com.enigma.online_dono_app.dto.request.UpdateStatusCampaign;
import com.enigma.online_dono_app.dto.response.CampaignResponse;
import com.enigma.online_dono_app.service.CampaignService;
import com.enigma.online_dono_app.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoint.API_CAMPAIGN)
@RequiredArgsConstructor
public class CampaignController {
    private final CampaignService campaignService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<?> createCampaign(@RequestBody CampaignRequest campaignRequest) {
        CampaignResponse newCampaign = campaignService.createCampaign(campaignRequest);
        return ResponseUtils.buildResponse(HttpStatus.CREATED,"Success Create Campaign", newCampaign);
    }

    @PreAuthorize("hasAnyRole('ADMIN') or @permissionServiceImpl.hasPermission(#id, authentication.principal)")
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateCampaign(@PathVariable("id") String id, @RequestBody UpdateCampaignRequest updateCampaignRequest) {
        CampaignResponse updatedCampaign = campaignService.updateCampaign(id, updateCampaignRequest);
        return ResponseUtils.buildResponse(HttpStatus.OK,"Success Update Campaign", updatedCampaign);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> updateStatusCampaign(@PathVariable("id") String id, @RequestBody UpdateStatusCampaign updateStatusCampaign) {
        CampaignResponse updatedCampaign = campaignService.updateStatusCampaign(id, updateStatusCampaign);
        return ResponseUtils.buildResponse(HttpStatus.OK,"Success Update Status Campaign", updatedCampaign);
    }

    @PreAuthorize("hasAnyRole('USER') and @permissionServiceImpl.hasPermission(#id, authentication.principal)")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteCampaign(@PathVariable("id") String id) {
        String deletedCampaign = campaignService.deleteCampaign(id);
        return ResponseUtils.buildResponse(HttpStatus.OK,"Success Delete Campaign", deletedCampaign+" deleted");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<?> getAllCampaigns(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size
    ) {
        Page<CampaignResponse> campaigns = campaignService.getAllCampaigns(page, size);
        return ResponseUtils.buildPageResponse(HttpStatus.OK,"Success Get All Campaigns", campaigns);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getCampaign(@PathVariable("id") String id) {
        CampaignResponse campaign = campaignService.getCampaign(id);
        return ResponseUtils.buildResponse(HttpStatus.OK,"Success Get Campaign", campaign);
    }


}
