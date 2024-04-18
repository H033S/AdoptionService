package com.expeditors.adoption.controllers;

import com.expeditors.adoption.domain.entities.Adoption;
import com.expeditors.adoption.dto.adoption.AddRequestDTO;
import com.expeditors.adoption.factory.JsonConverter;
import com.expeditors.adoption.factory.TestFactory;
import com.expeditors.adoption.service.implementation.AdopterServiceImpl;
import com.expeditors.adoption.service.implementation.AdoptionServiceImpl;
import com.expeditors.adoption.service.implementation.PetServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AdoptionControllerTest {
    
    @MockBean
    private AdoptionServiceImpl adoptionService;

    @MockBean
    private PetServiceImpl petService;

    @MockBean
    private AdopterServiceImpl adopterService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /adoption/all - Success")
    public void getAllAdoption_ReturnsOk() throws Exception{
        
        var mockAdoption1 = TestFactory.getAdoptionInstance();
        var mockAdoption2 = TestFactory.getAdoptionInstance();
        var mockListOfAdoption = List.of(
                mockAdoption1,
                mockAdoption2);

        Mockito.doReturn(mockListOfAdoption)
                .when(adoptionService).findAllAdoptions();

        mockMvc.perform(get("/adoption/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$.[0].adopterResponse.id", is(1)))
                .andExpect(jsonPath("$.[0].petResponse.id", is(1)));
    }

    @Test
    @DisplayName("GET /adoption/{id} - Found")
    public void getAdoptionById_ReturnOk_WithCorrectId() throws Exception {
        var mockAdoption1 = TestFactory.getAdoptionInstance();

        Mockito.doReturn(mockAdoption1)
                .when(adoptionService).findAdoptionById(1);

        mockMvc.perform(get("/adoption/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.adopterResponse.id", is(1)))
                .andExpect(jsonPath("$.petResponse.id", is(1)));
    }

    @Test
    @DisplayName("GET /adoption/{id} - Not Found")
    public void getAdoptionById_ReturnNotFound_WithIncorrectId() throws Exception {

        Mockito.doReturn(null)
                .when(adoptionService).findAdoptionById(1);

        mockMvc.perform(get("/adoption/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /adoption - Success")
    public void addAdoption_ReturnsOk_WithValidObject() throws Exception {
        var mockDate = LocalDate.now().plusDays(1);
        var mockAdoptionRequest = AddRequestDTO.AddAdoptionRequest
                .builder()
                .adoptionDate(mockDate)
                .petId(1)
                .adopterId(1)
                .build();

        var mockAdopter = TestFactory.getAdopterInstance();
        var mockPet = TestFactory.getPetInstance();
        var mockAdoption = new Adoption(1, mockAdopter, mockPet, mockDate);

        Mockito.doReturn(mockAdopter)
                .when(adopterService).getAdopterById(1);
        Mockito.doReturn(mockPet)
                .when(petService).getPetById(1);
        Mockito.doReturn(mockAdoption)
                .when(adoptionService).addNewAdoption(any());

        var request = mockMvc.perform(
                post("/adoption")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.fromObject(mockAdoptionRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    @DisplayName("POST /adoption - Success")
    public void addAdoption_ReturnsBadRequest_WithInvalidObject() throws Exception {
        var mockDate = LocalDate.now().plusDays(1);
        var mockAdoptionRequest = AddRequestDTO.AddAdoptionRequest
                .builder()
                .adoptionDate(mockDate)
                .petId(1)
                .adopterId(1)
                .build();

        var mockAdopter = TestFactory.getAdopterInstance();
        var mockPet = TestFactory.getPetInstance();
        var mockAdoption = new Adoption(1, mockAdopter, mockPet, mockDate);

        Mockito.doReturn(null)
                .when(adopterService).getAdopterById(anyInt());
        Mockito.doReturn(null)
                .when(petService).getPetById(anyInt());
        Mockito.doReturn(mockAdoption)
                .when(adoptionService).addNewAdoption(any());

        mockMvc.perform(post("/adoption")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.fromObject(mockAdoptionRequest)))
                .andExpect(status().isBadRequest());

    }


    @Test
    @DisplayName("PUT /adoption/{id} - Success")
    public void updateAdoption_ReturnsOk_WhenIdIsFoundAndBodyIsValidToGenerateAdoption(){
        //TODO Finish updateAdoption_ReturnsOk_WhenIdIsFoundAndBodyIsValidToGenerateAdoption
    }

    @Test
    @DisplayName("PUT /adoption/{id} - Bad Request")
    public void updateAdoption_ReturnsBadRequest_WhenIdIsFoundAndBodyIsNotValidToGenerateAdoption(){
        //TODO Finish updateAdoption_ReturnsBadRequest_WhenIdIsFoundAndBodyIsNotValidToGenerateAdoption
    }

    @Test
    @DisplayName("PUT /adoption/{id} - Internal Server Error")
    public void updateAdoption_ReturnsBadRequest_WhenAdoptionCannotBeUpdated(){
        //TODO Finish updateAdoption_ReturnsBadRequest_WhenAdoptionCannotBeUpdated
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
