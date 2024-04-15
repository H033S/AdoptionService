package com.expeditors.adoption.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import com.expeditors.adoption.domain.entities.Adopter;
import com.expeditors.adoption.domain.entities.Adoption;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.expeditors.adoption.dto.AdoptionRequest;
import com.expeditors.adoption.dto.AdoptionResponse;
import com.expeditors.adoption.service.AdoptionServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/adoption")
public class AdoptionController {
    
    AdoptionServiceImpl adoptionService;

    public AdoptionController(AdoptionServiceImpl adoptionService) {
        this.adoptionService = adoptionService;
    }

    @GetMapping("/all")
    public List<AdoptionResponse> getAdoptions(){

        return adoptionService.findAllAdoptions()
                .stream()
                .map(AdoptionResponse::fromAdoption)
                .toList();
    }

    @GetMapping("/all/{adoptionDate}")
    public List<Adopter> getAdoptersByAdoptionDate(
            @PathVariable("adoptionDate")LocalDate adoptionDate){

        return adoptionService.getAdopterBy(
                adoption -> adoption.getAdoptionDate().equals(adoptionDate));
    }

    @GetMapping("/{id}")
    public AdoptionResponse getAdoption(
            @PathVariable("id") int adoptionId){

        var adoption = adoptionService.findAdoptionById(adoptionId);

        if(Objects.isNull(adoption)) {
            throw new IllegalArgumentException(
                    "Cannot return an adoption for Id = " + adoptionId
            );

        }
        return AdoptionResponse.fromAdoption(adoption);
    }

    @PostMapping
    public AdoptionResponse addAdoption(
        @Valid @RequestBody AdoptionRequest adoptionRequest){

            var adoption = adoptionService.addNewAdoption(
                adoptionRequest.toAdoption()
            );

            return AdoptionResponse
            .fromAdoption(adoption);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdoption(
            @PathVariable("id") int adoptionId){

        var adoption = adoptionService.findAdoptionById(adoptionId);

        if(Objects.isNull(adoption)){
            return ResponseEntity.notFound().build();
        }

        if(adoptionService.deleteAdoption(adoptionId)){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.internalServerError().build();
    }
}
