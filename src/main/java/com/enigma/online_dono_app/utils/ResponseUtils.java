package com.enigma.online_dono_app.utils;

import com.enigma.online_dono_app.dto.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {

    public static  <T> ResponseEntity<CommonResponse<T>> buildCommonResponse(HttpStatus status, String message,T data) {
        CommonResponse<T> commonResponse = new CommonResponse<>(status, message, data);
        return ResponseEntity.status(status).body(commonResponse);
    }
}
