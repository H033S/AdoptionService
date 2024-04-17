package com.expeditors.adoption.service;

import com.expeditors.adoption.dao.CrudDAO;
import com.expeditors.adoption.domain.entities.Adopter;
import com.expeditors.adoption.domain.entities.Adoption;
import com.expeditors.adoption.factory.TestFactory;
import com.expeditors.adoption.service.implementation.AdoptionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.hamcrest.Matchers.containsInRelativeOrder;

@ExtendWith(MockitoExtension.class)
public class AdoptionServiceTest {

    @Mock
    CrudDAO<Adoption> adoptionDAO;
    @InjectMocks
    AdoptionServiceImpl adoptionService;

    @Test
    public void addNewAdoption_RunSuccessfully_WithNotNullValue(){
        var mockAdoption = TestFactory.getAdoptionInstance();

        doReturn(mockAdoption)
                .when(adoptionDAO).insert(mockAdoption);

        var adoption = adoptionService.addNewAdoption(mockAdoption);

        assertFalse(Objects.isNull(adoption));
        assertSame(mockAdoption, adoption);
    }

    @Test
    public void addNewAdoption_ThrowsIllegalArgumentException_WhenAdoptionPassedIsNull(){

        assertThrows(IllegalArgumentException.class,
                () -> adoptionService.addNewAdoption(null));
    }

    @Test
    public void updateAdoption_ReturnTrue_WhenIdIsFoundInRepo(){
        var mockAdoption = TestFactory.getAdoptionInstance();

        doReturn(true)
                .when(adoptionDAO).update(mockAdoption);

        assertTrue(adoptionService.updateAdoption(mockAdoption));
    }

    @Test
    public void updateAdoption_ReturnFalse_WhenIdIsNotFoundInRepo(){
        var mockAdoption = TestFactory.getAdoptionInstance();

        doReturn(false)
                .when(adoptionDAO).update(mockAdoption);

        assertFalse(adoptionService.updateAdoption(mockAdoption));
    }

    @Test
    public void updateAdoption_ThrowsIllegalArgumentException_WhenAdoptionPassedIsNull(){
        assertThrows(IllegalArgumentException.class,
                () -> adoptionService.updateAdoption(null));
    }

    @Test
    public void deleteAdoption_ReturnsTrue_WhenIdIsFound(){
        doReturn(true)
                .when(adoptionDAO).delete(1);

        assertTrue(adoptionService.deleteAdoption(1));
    }

    @Test
    public void deleteAdoption_ReturnsFalse_WhenIdIsNotFound(){
        doReturn(false)
                .when(adoptionDAO).delete(1);

        assertFalse(adoptionService.deleteAdoption(1));
    }

    @Test
    public void findAdoptionsById_ReturnAdoption_WhenIdIsFound(){
        var mockAdoption = TestFactory.getAdoptionInstance();

        doReturn(mockAdoption)
                .when(adoptionDAO).findById(1);

        var objectFound = adoptionService.findAdoptionById(1);

        assertSame(mockAdoption, objectFound);
    }

    @Test
    public void findAdoptionsById_ReturnNull_WhenIdIsNotFound(){
        doReturn(null)
                .when(adoptionDAO).findById(1);

        var objectFound = adoptionService.findAdoptionById(1);

        assertNull(objectFound);
    }

    @Test
    public void findAllAdoptions_RunSuccessful(){
        var mockAdoptionList = List.of(
                TestFactory.getAdoptionInstance(),
                TestFactory.getAdoptionInstance(),
                TestFactory.getAdoptionInstance(),
                TestFactory.getAdoptionInstance(),
                TestFactory.getAdoptionInstance()
        );

        doReturn(mockAdoptionList)
                .when(adoptionDAO).findAll();

        var adoptionListReturned = adoptionService.findAllAdoptions();

        assertEquals(mockAdoptionList.size(), adoptionListReturned.size());
        assertSame(mockAdoptionList, adoptionListReturned);
    }

    @Test
    public void getAdoptersSortedByDateOfAdoption_RunSuccessful(){

        var adopter1 = new Adopter(1,  "Antonio Nazco", "123-456-789");
        var adopter2 = new Adopter(1,  "Nathaly Nazco", "123-456-789");

        var ad1 = new Adoption(
                1,
                adopter1,
                TestFactory.getPetInstance(),
                LocalDate.now().minusDays(2));

        var ad2 = new Adoption(
                2,
                adopter2,
                TestFactory.getPetInstance(),
                LocalDate.now().minusDays(3));


        doReturn(List.of(ad2,ad1))
                .when(adoptionDAO).findAll();

        var result = adoptionService
                .getAdoptersSortedByDateOfAdoption();

        assertEquals(2, result.size());
        assertThat(result, containsInRelativeOrder(adopter2, adopter1));
    }


    @Test
    public void getAdoptersSortedByName_RunSuccessful(){

        var adopter1 = new Adopter(1,  "Antonio Nazco", "123-456-789");
        var adopter2 = new Adopter(1,  "Nathaly Nazco", "123-456-789");

        var ad1 = new Adoption(
                1,
                adopter1,
                TestFactory.getPetInstance(),
                LocalDate.now().minusDays(2));

        var ad2 = new Adoption(
                2,
                adopter2,
                TestFactory.getPetInstance(),
                LocalDate.now().minusDays(3));


        doReturn(List.of(ad2,ad1))
                .when(adoptionDAO).findAll();

        var result = adoptionService
                .getAdoptersByName("Antonio Nazco");

        assertEquals(1, result.size());
        assertThat(result, contains( adopter1));
    }
}
