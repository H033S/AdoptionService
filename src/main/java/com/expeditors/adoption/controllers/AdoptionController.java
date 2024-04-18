package com.expeditors.adoption.controllers;

import java.time.LocalDate;
import java.util.Objects;

import com.expeditors.adoption.dto.adoption.AddRequestDTO;
import com.expeditors.adoption.dto.adoption.AdoptionResponseDTO;
import com.expeditors.adoption.service.AdopterService;
import com.expeditors.adoption.service.AdoptionService;
import com.expeditors.adoption.service.PetService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.expeditors.adoption.service.implementation.AdoptionServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/adoption")
public class AdoptionController {
    
    private final AdoptionService adoptionService;
    private final PetService petService;
    private final AdopterService adopterService;

    public AdoptionController(
            AdoptionServiceImpl adoptionService,
            PetService petService,
            AdopterService adopterService) {

        this.adoptionService = adoptionService;
        this.petService = petService;
        this.adopterService = adopterService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> listAdoptions(){

        return ResponseEntity.ok(
                adoptionService.findAllAdoptions()
                        .stream()
                        .map(AdoptionResponseDTO::createFromAdoption)
                        .toList());
    }

    @GetMapping("/all/{adoptionDate}")
    public ResponseEntity<?> listAdoptionsByDate(
            @PathVariable("adoptionDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate adoptionDate){

        return ResponseEntity.ok(
                adoptionService
                        .findAllAdoptions()
                        .stream()
                        .filter(adoption -> adoption.getAdoptionDate().equals(adoptionDate))
                        .map(AdoptionResponseDTO::createFromAdoption)
                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdoption(
            @PathVariable("id") int adoptionId){

        var adoption = adoptionService.findAdoptionById(adoptionId);

        if(Objects.isNull(adoption)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                AdoptionResponseDTO.createFromAdoption(adoption)
        );
    }

    @PostMapping
    public ResponseEntity<?> addAdoption(
         @Valid @RequestBody AddRequestDTO.AddAdoptionRequest adoptionRequest){

        var associatedPet = petService.getPetById(adoptionRequest.getPetId());
        var associatedAdopter = adopterService.getAdopterById(adoptionRequest.getAdopterId());
        var adoption = AddRequestDTO.createAdoption(
                adoptionRequest.getAdoptionDate(),
                associatedAdopter,
                associatedPet);

        if(adoption.isModelValid()){
            var adoptionCreatedResult = adoptionService.addNewAdoption(adoption);
            return ResponseEntity.ok(
                    AdoptionResponseDTO.createFromAdoption(adoptionCreatedResult));
        }

        return ResponseEntity.badRequest()
                .body(adoption.getModelViolations());
    }

    @PutMapping("/{adoptionId}")
    public ResponseEntity<?> updateAdoption(
            @PathVariable("adoptionId") int adoptionId,
            @Valid @RequestBody AddRequestDTO.AddAdoptionRequest adoptionRequest){

        var associatedPet = petService.getPetById(adoptionRequest.getPetId());
        var associatedAdopter = adopterService.getAdopterById(adoptionRequest.getAdopterId());
        var adoption = AddRequestDTO.createAdoption(
                adoptionRequest.getAdoptionDate(),
                associatedAdopter,
                associatedPet);
        adoption.setId(adoptionId);

        if(adoption.isModelValid()){
            var isAdoptionCreatedSuccessfully = adoptionService.updateAdoption(adoption);

            return isAdoptionCreatedSuccessfully ?
                    ResponseEntity.ok().build():
                    ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.badRequest()
                .body(adoption.getModelViolations());
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
