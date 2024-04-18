package com.expeditors.adoption.controllers;

import com.expeditors.adoption.domain.entities.Adoption;
import com.expeditors.adoption.dto.adoption.AddRequestDTO;
import com.expeditors.adoption.factory.JsonConverter;
import com.expeditors.adoption.factory.TestFactory;
import com.expeditors.adoption.service.implementation.AdopterServiceImpl;
import com.expeditors.adoption.service.implementation.AdoptionServiceImpl;
import com.expeditors.adoption.service.implementation.PetServiceImpl;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class NewAdoptionControllerTest {
    
    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("POST /adoption - Success")
    public void addAdoption_ReturnsOk_WithValidObject() throws Exception {

        var adoptionRequest = AddRequestDTO.AddAdoptionRequest
                .builder()
                .adoptionDate(LocalDate.of(2024, 10, 10))
                .petId(1)
                .adopterId(1)
                .build();


        var request = mockMvc.perform(
                post("/adoption")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.fromObject(adoptionRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }
}
