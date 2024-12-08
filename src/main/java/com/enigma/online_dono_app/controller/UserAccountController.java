package com.enigma.online_dono_app.controller;

import com.enigma.online_dono_app.constant.Endpoint;
import com.enigma.online_dono_app.dto.request.UpdateAccountRequest;
import com.enigma.online_dono_app.dto.response.LogResponse;
import com.enigma.online_dono_app.dto.response.RegisterResponse;
import com.enigma.online_dono_app.service.UserAccountService;
import com.enigma.online_dono_app.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoint.API_USER)
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountService userAccountService;

    @PreAuthorize("hasRole('USER')")
    @PutMapping
    public ResponseEntity<?> updateUserAccount(@RequestBody UpdateAccountRequest updateAccountRequest) {
        RegisterResponse updatedUserAccount = userAccountService.updateUserAccount(updateAccountRequest);
        return ResponseUtils.buildResponse(HttpStatus.OK,"Succes Update Account", updatedUserAccount);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAllUserAccounts(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size) {
        Page<RegisterResponse> allUser = userAccountService.findAllUserAccounts(page,size);
        return ResponseUtils.buildPageResponse(HttpStatus.OK,"Succes Get All User Accounts", allUser);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(path = "/logs")
    public ResponseEntity<?> getUserAccountLogs(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size) {
        Page<LogResponse> allUserLog = userAccountService.findAllLogs(page,size);
        return ResponseUtils.buildPageResponse(HttpStatus.OK,"Succes Get All User Logs", allUserLog);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/logs/{id}")
    public ResponseEntity<?> getUserAccountLogsByUserId(
            @PathVariable("id") String id,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size) {
        Page<LogResponse> allUserLog = userAccountService.findAllLogsByUserId(page,size,id);
        return ResponseUtils.buildPageResponse(HttpStatus.OK,"Succes Get All User Logs", allUserLog);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping
    public ResponseEntity<?> deleteUserAccount(){
        String deletedUserAccount = userAccountService.deleteUserAccount();
        return ResponseUtils.buildResponse(HttpStatus.OK,"Succes Delete User Account", "UserAccount Deleted -> "+deletedUserAccount);
    }
}
