package com.enigma.online_dono_app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingResponse {
    private Long totalElements;
    private Integer totalPages;
    private Integer pageSize;
    private Integer pageNumber;

}
