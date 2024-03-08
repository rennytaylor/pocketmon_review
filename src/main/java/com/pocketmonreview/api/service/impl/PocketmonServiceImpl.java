package com.pocketmonreview.api.service.impl;

import com.pocketmonreview.api.dto.PocketmonDto;
import com.pocketmonreview.api.dto.PocketmonResponse;
import com.pocketmonreview.api.exceptions.PocketmonNotFoundException;
import com.pocketmonreview.api.models.Pocketmon;
import com.pocketmonreview.api.repository.PocketmonRepository;
import com.pocketmonreview.api.service.PocketmonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PocketmonServiceImpl implements PocketmonService {

    private PocketmonRepository pocketmonRepository;

    @Autowired
    public PocketmonServiceImpl(PocketmonRepository pocketmonRepository){
        this.pocketmonRepository = pocketmonRepository;
    }
    @Override
    public PocketmonDto createPocketmon(PocketmonDto pocketmonDto) {
        Pocketmon pocketmon = new Pocketmon();
        pocketmon.setName(pocketmonDto.getName());
        pocketmon.setType(pocketmonDto.getType());

        Pocketmon newPocketmon = pocketmonRepository.save(pocketmon);

        PocketmonDto pocketmonResponse = new PocketmonDto();
        pocketmonResponse.setId(newPocketmon.getId());
        pocketmonResponse.setName(newPocketmon.getName());
        pocketmonResponse.setType(newPocketmon.getType());
        return pocketmonResponse;
    }

    @Override
    public PocketmonResponse getAllPocketmon(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Pocketmon> pocketmon = pocketmonRepository.findAll(pageable);
        List<Pocketmon> listOfPocketmon = pocketmon.getContent();
        List<PocketmonDto> content = listOfPocketmon.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        PocketmonResponse pocketmonResponse = new PocketmonResponse();
        pocketmonResponse.setContent(content);
        pocketmonResponse.setPageNumber(pocketmon.getNumber());
        pocketmonResponse.setPageSize(pocketmon.getSize());
        pocketmonResponse.setTotalElements(pocketmon.getTotalElements());
        pocketmonResponse.setTotalPages(pocketmon.getTotalPages());
        pocketmonResponse.setLast(pocketmon.isLast());

        return pocketmonResponse;
    }

    @Override
    public PocketmonDto getPocketmonById(int id) {
        Pocketmon pocketmon = pocketmonRepository.findById(id).orElseThrow(() -> new PocketmonNotFoundException("Pocketmon could not be found"));
        return mapToDto(pocketmon);
    }

    @Override
    public PocketmonDto updatePocketmon(PocketmonDto pocketmonDto, int id) {
        Pocketmon pocketmon = pocketmonRepository.findById(id).orElseThrow(() -> new PocketmonNotFoundException("Pocketmon could not be updated"));

        pocketmon.setName(pocketmonDto.getName());
        pocketmon.setType(pocketmonDto.getType());

        Pocketmon updatedPocketmon = pocketmonRepository.save(pocketmon);
        return mapToDto(updatedPocketmon);
    }

    @Override
    public void deletePocketmonId(int id) {
        Pocketmon pocketmon = pocketmonRepository.findById(id).orElseThrow(() -> new PocketmonNotFoundException("Pocketmon could not be deleted"));
        pocketmonRepository.delete(pocketmon);
    }

    private PocketmonDto mapToDto(Pocketmon pocketmon){
        PocketmonDto pocketmonDto = new PocketmonDto();
        pocketmonDto.setId(pocketmon.getId());
        pocketmonDto.setName(pocketmon.getName());
        pocketmonDto.setType(pocketmon.getType());
        return pocketmonDto;
    }

    private Pocketmon mapToEntity(PocketmonDto pocketmonDto){
        Pocketmon pocketmon = new Pocketmon();
        pocketmon.setName(pocketmonDto.getName());
        pocketmon.setType(pocketmonDto.getType());
        return pocketmon;
    }
}
