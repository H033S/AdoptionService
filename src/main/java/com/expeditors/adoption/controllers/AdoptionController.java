package com.expeditors.adoption.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expeditors.adoption.domain.entities.Adoption;
import com.expeditors.adoption.dto.AdoptionRequest;
import com.expeditors.adoption.dto.AdoptionResponse;
import com.expeditors.adoption.service.AdoptionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/adoption")
public class AdoptionController {
    
    AdoptionService adoptionService;

    public AdoptionController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }

    @GetMapping
    public List<Adoption> getAdoption(){
        return adoptionService.findAllAdoptions();
    }

    @PostMapping
    public AdoptionResponse getAdoption(
        @Valid @RequestBody AdoptionRequest adoptionRequest){

            var adoption = adoptionService.addNewAdoption(
                adoptionRequest.toAdoption()
            );

            return AdoptionResponse
            .fromAdoption(adoption);
    }
}
