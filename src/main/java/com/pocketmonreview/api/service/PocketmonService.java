package com.pocketmonreview.api.service;

import com.pocketmonreview.api.dto.PocketmonDto;
import com.pocketmonreview.api.dto.PocketmonResponse;

import java.util.List;

public interface PocketmonService {
    PocketmonDto createPocketmon(PocketmonDto pocketmonDto);

    PocketmonResponse getAllPocketmon(int pageNumber, int pageSize);

    PocketmonDto getPocketmonById(int id);

    PocketmonDto updatePocketmon(PocketmonDto pocketmonDto, int id);

    void deletePocketmonId(int id);
}
