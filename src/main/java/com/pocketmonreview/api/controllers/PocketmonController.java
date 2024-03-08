package com.pocketmonreview.api.controllers;

import com.pocketmonreview.api.dto.PocketmonDto;
import com.pocketmonreview.api.dto.PocketmonResponse;
import com.pocketmonreview.api.service.PocketmonService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PocketmonController {

    private PocketmonService pocketmonService;

    @Autowired
    public PocketmonController(PocketmonService pocketmonService) {
        this.pocketmonService = pocketmonService;
    }

    @GetMapping("pocketmon")
    public ResponseEntity<PocketmonResponse> getPocketmons(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(pocketmonService.getAllPocketmon(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("pocketmon/{id}")
    public ResponseEntity<PocketmonDto> pocketmonDetail(@PathVariable int id){
        return ResponseEntity.ok(pocketmonService.getPocketmonById(id));
    }
    
    @PostMapping("pocketmon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PocketmonDto> createPocketmon(@RequestBody PocketmonDto pocketmonDto){
        return new ResponseEntity<>(pocketmonService.createPocketmon(pocketmonDto), HttpStatus.CREATED);
    }

    @PutMapping("pocketmon/{id}/update")
    public ResponseEntity<PocketmonDto> updatePocketmon(@RequestBody PocketmonDto pocketmonDto, @PathVariable("id") int pocketmonId){
        PocketmonDto response = pocketmonService.updatePocketmon(pocketmonDto, pocketmonId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("pocketmon/{id}/delete")
    public ResponseEntity<String> deletePocketmon(@PathVariable("id") int pocketmonId){
        pocketmonService.deletePocketmonId(pocketmonId);
        return new ResponseEntity<>("Pocketmon deleted", HttpStatus.OK);
    }
}
