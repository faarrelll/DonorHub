package com.enigma.online_dono_app.controller;

import com.enigma.online_dono_app.constant.Endpoint;
import com.enigma.online_dono_app.dto.request.DonationRequest;
import com.enigma.online_dono_app.dto.request.UpdateStatusDonation;
import com.enigma.online_dono_app.dto.response.DonationResponse;
import com.enigma.online_dono_app.service.DonationService;
import com.enigma.online_dono_app.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoint.API_DONATION)
@RequiredArgsConstructor

public class DonationController {

    private final DonationService donationService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(path = "/{id}")
    public ResponseEntity<?> makeDonation(
            @PathVariable String id,
            @RequestBody DonationRequest donationRequest) {
        DonationResponse getDonation = donationService.createDonation(id,donationRequest);
        return ResponseUtils.buildResponse(HttpStatus.CREATED,"Succes Make Donation",getDonation);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> updateDonation(
            @PathVariable String id,
            @RequestBody UpdateStatusDonation updateStatusDonation) {
        DonationResponse updateDonation = donationService.updateStatusDonation(id,updateStatusDonation);
        return ResponseUtils.buildResponse(HttpStatus.OK,"Succes Paid Donation",updateDonation);
    }

}
