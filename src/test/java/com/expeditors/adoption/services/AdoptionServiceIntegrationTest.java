package com.expeditors.adoption.services;

import com.expeditors.adoption.factory.TestFactory;
import com.expeditors.adoption.service.AdoptionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JDBC_TEST;

@SpringBootTest
@ActiveProfiles(JDBC_TEST)
@Sql(scripts = {"/sql/h2/0-schema.sql", "/sql/h2/1-test-data.sql"})
public class AdoptionServiceIntegrationTest {

    @Autowired
    AdoptionService adoptionService;

    @Test
    void findAll_RunSuccessfully(){

        var adoptions = adoptionService.getAllEntities();
        Assertions.assertEquals(3, adoptions.size());
    }

    @Test
    void findById_RunSuccessfully_WhenIdIsFound(){

        var adoption = adoptionService.getEntityById(1);
        Assertions.assertEquals(1, adoption.getId());
    }

    @Test
    void findById_RunSuccessfully_WhenIdIsNotFound(){

        var adoption = adoptionService.getEntityById(25);
        Assertions.assertNull(adoption);
    }

    @Test
    void addEntity_RunSuccessfully_WhenCorrectAdoption(){

        var adoption = TestFactory.getAdoptionInstance();
        var adoptionResult = adoptionService.addEntity(adoption);

        Assertions.assertEquals(4, adoptionResult.getId());
    }

    @Test
    void addEntity_ThrowsIllegalArgumentException_WhenAdoptionIsNull(){

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> adoptionService.addEntity(null));
    }

    @Test
    void updateEntity_ThrowsIllegalArgumentException_WhenAdoptionIsNull(){

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> adoptionService.updateEntity(null));
    }

    @Test
    void updateEntity_RunsSuccessfully_WhenAdoptionIsValid(){

        var date = LocalDate.now().plusDays(2);
        var adoption = TestFactory.getAdoptionInstance();
        adoption.setAdoptionDate(date);

        Assertions.assertTrue(adoptionService.updateEntity(adoption));
        Assertions.assertEquals(date, adoption.getAdoptionDate());
    }

    @Test
    void deleteEntity_RunSuccessfully_WhenIdIsValid(){

        Assertions.assertTrue(adoptionService.deleteEntity(1));
    }

    @Test
    @Sql(scripts = {"/sql/h2/0-schema.sql"})
    void deleteEntity_RunSuccessfully_WhenIdIsNotValid(){

        Assertions.assertFalse(adoptionService.deleteEntity(1));
    }

}
