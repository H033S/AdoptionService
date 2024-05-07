package com.expeditors.adoption.unittests.services;

import com.expeditors.adoption.dao.BaseDAO;
import com.expeditors.adoption.domain.entities.Adopter;
import com.expeditors.adoption.domain.entities.Adoption;
import com.expeditors.adoption.domain.entities.Pet;
import com.expeditors.adoption.factory.TestFactory;
import com.expeditors.adoption.service.implementation.AdoptionServiceImpl;
import com.expeditors.adoption.service.implementation.PetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
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

@Nested
@ExtendWith(MockitoExtension.class)
class AdoptionServiceTest
    extends BaseServiceTest<Adoption>{

    @Mock
    private BaseDAO<Adoption> adoptionBaseDAO;
    @InjectMocks
    private AdoptionServiceImpl adoptionService;


    @Override
    @BeforeEach
    public void init() {
        baseDAO = adoptionBaseDAO;
        baseService = adoptionService;
        mockEntity = TestFactory.getAdoptionInstance();
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
                .when(baseDAO).findAll();

        var result = adoptionService
                .getAdoptersSortedByDateOfAdoption();

        assertEquals(2, result.size());
        assertThat(result, containsInRelativeOrder(adopter2, adopter1));
    }


    @Test
    public void getAdoptersSortedByName_RunSuccessful() {

        var adopter1 = new Adopter(1, "Antonio Nazco", "123-456-789");
        var adopter2 = new Adopter(1, "Nathaly Nazco", "123-456-789");

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


        doReturn(List.of(ad2, ad1))
                .when(baseDAO).findAll();

        var result = adoptionService
                .getAdoptersByName("Antonio Nazco");

        assertEquals(1, result.size());
        assertThat(result, contains(adopter1));
    }

}
