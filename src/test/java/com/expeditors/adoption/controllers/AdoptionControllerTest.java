package com.expeditors.adoption.controllers;

import com.expeditors.adoption.domain.entities.Adoption;
import com.expeditors.adoption.factory.TestFactory;
import com.expeditors.adoption.service.AdoptionServiceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.Mockito;

import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdoptionControllerTest {
    
    @MockBean
    private AdoptionServiceImpl adoptionService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /adoption/all - Found")
    //TODO: Pending Verification of the Response Json
    public void getAllAdoption_RunSuccessfully() throws Exception{
        
        var mockAdoption1 = TestFactory.getAdoptionInstance();
        var mockAdoption2 = TestFactory.getAdoptionInstance();

        Mockito.doReturn(List.of(
                mockAdoption1,
                mockAdoption2))
                .when(adoptionService).findAllAdoptions();


        mockMvc.perform(get("/adoption/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(
                MediaType.APPLICATION_JSON));
    }


    @Test
    @DisplayName("GET /adoption/{id} - Found")
    //TODO: Pending Verification of the Response Json
    public void getAdoptionById_ReturnObject_WithCorrectId() throws Exception {
        var mockAdoption1 = TestFactory.getAdoptionInstance();

        Mockito.doReturn(mockAdoption1)
                .when(adoptionService).findAdoptionById(1);

        mockMvc.perform(get("/adoption/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(
                        MediaType.APPLICATION_JSON))
                //Response
                .andExpect(jsonPath("$.adopter.id",is(mockAdoption1.getAdopter().getId())))
                .andExpect(jsonPath("$.adopter.adopterName", is(mockAdoption1.getAdopter().getAdopterName())))
                .andExpect(jsonPath("$.adopter.phoneNumber", is(mockAdoption1.getAdopter().getPhoneNumber())))
                .andExpect(jsonPath("$.pet.id", is(mockAdoption1.getPet().getId())))
                .andExpect(jsonPath("$.pet.petBreed", is(mockAdoption1.getPet().getPetBreed().toString())))
                .andExpect(jsonPath("$.pet.petType", is(mockAdoption1.getPet().getPetType().toString())))
                .andExpect(jsonPath("$.pet.petName", is(mockAdoption1.getPet().getPetName())))
                .andExpect(jsonPath("$.adoptionDate", is(mockAdoption1.getAdoptionDate().toString())));
    }

    @Test
    @DisplayName("POST /adoption")
    public void addAdoption_ReturnsOk_WithValidObject() throws Exception {
        var mockAdoption = TestFactory.getAdoptionInstance();

        Mockito.doReturn(mockAdoption)
                .when(adoptionService).addNewAdoption(mockAdoption);

        mockMvc.perform(
                post("/adoption")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockAdoption)));
    }

    private String asJsonString(Adoption adoption) {

        try{
            return new ObjectMapper().writeValueAsString(adoption);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("DELETE /adoption/{id}")
    public void deleteAdoption_ReturnsOk_WhenIdIsFound() throws Exception {
        var mockAdoption1 = TestFactory.getAdoptionInstance();

        Mockito.doReturn(mockAdoption1)
                .when(adoptionService).findAdoptionById(1);
        Mockito.doReturn(true)
                .when(adoptionService).deleteAdoption(1);

        mockMvc.perform(delete("/adoption/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /adoption/{id}")
    public void deleteAdoption_ReturnsNotFound_WhenIdIsNotFound() throws Exception {

        Mockito.doReturn(null)
                .when(adoptionService).findAdoptionById(1);

        mockMvc.perform(delete("/adoption/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /adoption/{id}")
    public void deleteAdoption_ReturnsInternalServerError_WhenIdIsFoundButWasNotDeleted() throws Exception {
        var mockAdoption1 = TestFactory.getAdoptionInstance();

        Mockito.doReturn(mockAdoption1)
                .when(adoptionService).findAdoptionById(1);
        Mockito.doReturn(false)
                .when(adoptionService).deleteAdoption(1);

        mockMvc.perform(delete("/adoption/{id}", 1))
                .andExpect(status().isInternalServerError());
    }
}
