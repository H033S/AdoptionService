package com.expeditors.adoption.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.*;

@ActiveProfiles(JDBC_TEST)
class AdoptionControllerIntegrationTest__JDBC extends AdoptionControllerIntegrationTest{}
@ActiveProfiles(JDBC_TEMPLATE_TEST)
class AdoptionControllerIntegrationTest__JDBC_TEMPLATE extends AdoptionControllerIntegrationTest{}
@ActiveProfiles(JPA_TEST)
class AdoptionControllerIntegrationTest__JPA extends AdoptionControllerIntegrationTest{}

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AdoptionControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void test(){}
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
