package com.expeditors.adoption.services.adopter;

import com.expeditors.adoption.factory.TestFactory;
import com.expeditors.adoption.service.AdopterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JDBC_TEST;

@SpringBootTest
@ActiveProfiles(JDBC_TEST)
@Sql(scripts = {"/sql/h2/0-schema.sql", "/sql/h2/1-test-data.sql"})
public class AdopterServiceIntegrationTest {

    @Autowired
    AdopterService adopterService;

    @Test
    void findAll_RunSuccessfully(){

        var adopters = adopterService.getAllEntities();
        Assertions.assertEquals(2, adopters.size());
    }

    @Test
    void findById_RunSuccessfully_WhenIdIsFound(){

        var adopter = adopterService.getEntityById(1);
        Assertions.assertEquals(1, adopter.getId());
    }

    @Test
    void findById_RunSuccessfully_WhenIdIsNotFound(){

        var adopter = adopterService.getEntityById(25);
        Assertions.assertNull(adopter);
    }

    @Test
    void addEntity_RunSuccessfully_WhenCorrectAdopter(){

        var adopter = TestFactory.getAdopterInstance();
        var adopterResult = adopterService.addEntity(adopter);

        Assertions.assertEquals(4, adopterResult.getId());
    }

    @Test
    void addEntity_ThrowsIllegalArgumentException_WhenAdopterIsNull(){

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> adopterService.addEntity(null));
    }

    @Test
    void updateEntity_RunsSuccessfully_WhenAdopterIsValid(){

        var adopter = TestFactory.getAdopterInstance();
        Assertions.assertTrue(adopterService.updateEntity(adopter));
        adopter.setAdopterName("Test");

        Assertions.assertEquals("Test", adopter.getAdopterName());
    }

    @Test
    void updateEntity_ThrowsIllegalArgumentException_WhenAdopterIsNull(){

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> adopterService.updateEntity(null));
    }

    @Test
    void deleteEntity_RunSuccessfully_WhenIdIsValid(){

        var numberOfAdopters = adopterService.getAllEntities().size();
        Assertions.assertTrue(adopterService.deleteEntity(1));
        Assertions.assertEquals(numberOfAdopters - 1, adopterService.getAllEntities().size());
    }

    @Test
    @Sql(scripts = {"/sql/h2/0-schema.sql"})
    void deleteEntity_RunSuccessfully_WhenIdIsNotValid(){

        var numberOfAdopters = adopterService.getAllEntities().size();
        Assertions.assertFalse(adopterService.deleteEntity(1));
        Assertions.assertEquals(numberOfAdopters, adopterService.getAllEntities().size());
    }

}
