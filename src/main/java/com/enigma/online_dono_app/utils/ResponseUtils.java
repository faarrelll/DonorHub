package com.enigma.online_dono_app.utils;

import com.enigma.online_dono_app.dto.response.CommonResponse;
import com.enigma.online_dono_app.dto.response.PageResponse;
import com.enigma.online_dono_app.dto.response.PagingResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseUtils {

    public static  <T> ResponseEntity<CommonResponse<T>> buildResponse(HttpStatus status, String message, T data) {
        CommonResponse<T> commonResponse = new CommonResponse<>(status, message, data);
        return ResponseEntity.status(status).body(commonResponse);
    }

    public static  <T> ResponseEntity<PageResponse<List<T>>> buildPageResponse(HttpStatus status, String message, Page<T> data) {
        PageResponse <List<T>> pageResponse = PageResponse.<List<T>>builder()
                .status(status)
                .message(message)
                .data(data.getContent())
                .pagingResponse(
                        PagingResponse.builder()
                                .pageNumber(data.getNumber()+1)
                                .pageSize(data.getSize())
                                .totalElements(data.getTotalElements())
                                .totalPages(data.getTotalPages())
                                .build()
                )
                .build();

        return ResponseEntity.status(status).body(pageResponse);
    }
}
