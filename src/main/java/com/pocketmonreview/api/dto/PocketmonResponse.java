package com.pocketmonreview.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class PocketmonResponse {
    private List<PocketmonDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

}
