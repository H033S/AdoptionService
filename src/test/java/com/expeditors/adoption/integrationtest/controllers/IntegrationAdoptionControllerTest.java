package com.expeditors.adoption.integrationtest.controllers;

import com.expeditors.adoption.domain.entities.Adoption;
import com.expeditors.adoption.dto.adoption.AddOrUpdateAdoptionRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationAdoptionControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

//    @Test
//    @DisplayName("POST /adoption - Success")
//    public void addAdoption_ReturnsOk_WithValidObject() throws Exception {
//        //TODO: Verify how to perform this test automatically
//
//        var postResponse = testRestTemplate.postForEntity(
//                URI.create("/adoption"),
//                AddOrUpdateAdoptionRequestDTO.builder()
//                        .adoptionDate(LocalDate.now().plusDays(1))
//                        .adopterId(4)
//                        .petId(1)
//                        .build(),
//                Adoption.class
//        );
//
//        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
//    }


//    @Test
//    @DisplayName("POST /adoption - Success")
//    public void addAdoption_ReturnsOk_WithValidObject() throws Exception {
//
//        var adoptionRequest = AddOrUpdateAdoptionRequestDTO
//                .builder()
//                .adoptionDate(LocalDate.now().plusDays(1))
//                .petId(1)
//                .adopterId(4)
//                .build();
//
//        var request = mockMvc.perform(
//                post("/adoption")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(JsonConverter.fromObject(adoptionRequest)))
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
//    }

}
