package com.expeditors.adoption.controllers;

import com.expeditors.adoption.domain.entities.Adoption;
import com.expeditors.adoption.dto.adoption.AddOrUpdateAdoptionRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.net.URI;
import java.time.LocalDate;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.*;

@ActiveProfiles({JDBC, H2})
class AdoptionControllerIntegrationTest__JDBC extends AdoptionControllerIntegrationTest{}

@ActiveProfiles({JDBC_TEMPLATE, H2})
class AdoptionControllerIntegrationTest__JDBC_TEMPLATE extends AdoptionControllerIntegrationTest{}

@ActiveProfiles({JDBC_CLIENT, H2})
class AdoptionControllerIntegrationTest__JDBC_CLIENT extends AdoptionControllerIntegrationTest{}

@ActiveProfiles({JPA, H2})
class AdoptionControllerIntegrationTest__JPA extends AdoptionControllerIntegrationTest{}

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/sql/h2/0-schema.sql", "/sql/h2/1-test-data.sql"})
public abstract class AdoptionControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Post /adoption - Success")
    public void AddAdoption_ReturnsOk_WithValidObject() throws Exception {
        //todo: verify how to perform this test automatically

        var postResponse = testRestTemplate.postForEntity(
                URI.create("/adoption"),
                AddOrUpdateAdoptionRequestDTO.builder()
                        .adoptionDate(LocalDate.now().plusDays(1))
                        .petId(1)
                        .adopterId(1)
                        .build(),
                Adoption.class
        );

        Assertions.assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
    }

}
