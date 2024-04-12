package com.expeditors.adoption.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.expeditors.adoption.factory.TestFactory;
import com.expeditors.adoption.service.AdoptionService;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdoptionControllerTest {
    
    @MockBean
    private AdoptionService adoptionService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /adoption - Found")
    public void getAllAdoption_RunSuccessfully() throws Exception{
        
        var mockAdoption1 = TestFactory.getAdoptionInstance();
        var mockAdoption2 = TestFactory.getAdoptionInstance();

        doReturn(List.of(mockAdoption1, mockAdoption2))
            .when(adoptionService).findAllAdoptions();
        
        mockMvc.perform(get("/adoption"))

        //Response
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))

        //Header
        //.andExpect(header().string(HttpHeaders.ETAG, ""))
        //.andExpect(header().string(HttpHeaders.LOCATION, "/adoption"))

        //Body
        .andExpect(jsonPath("$.id", is(1)));
    }

}
