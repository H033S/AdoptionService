package com.expeditors.adoption.service;

import com.expeditors.adoption.domain.entities.Adopter;
import com.expeditors.adoption.domain.entities.Adoption;
import com.expeditors.adoption.factory.TestFactory;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class AdoptionServiceTest {

    @Autowired
    protected AdoptionService adoptionService;

    protected Adoption adoptionToAdd;

    @BeforeEach
    void setUp() {
        adoptionToAdd = TestFactory.getAdoptionInstance();
        adoptionService.clearAdoptions();
    }


    @Test
    public void addNewAdoption_NullVerificationThrowsException(){
        assertThrows(NullPointerException.class,
                () -> adoptionService.addNewAdoption(null));
    }

    @Test
    public void addNewAdoption_SuccessfullyAddedObject(){
        var adoptionReceived  = adoptionService.addNewAdoption(adoptionToAdd);
        assertEquals(adoptionToAdd, adoptionReceived);
    }

    @Test
    public void updateAdoption_ReturnsTrueAfterUpdate()
    {
        adoptionService.addNewAdoption(adoptionToAdd);
        Adoption adoptionToUpdate = TestFactory.getAdoptionInstance();

        var adopter = new Adopter(2, "Hello World", "561-767-5936");
        adoptionToUpdate.setAdopter(adopter);

        assertTrue(adoptionService.updateAdoption(adoptionToUpdate));
    }

    @Test
    public void updateAdoption_ReturnsFalseBecauseInvalidId()
    {
        adoptionService.addNewAdoption(adoptionToAdd);
        Adoption adoptionToUpdate = TestFactory.getAdoptionInstance();
        adoptionToUpdate.setId(15);

        var updateAdoptionResult = adoptionService.updateAdoption(adoptionToUpdate);        
        assertFalse(updateAdoptionResult);
    }

    @Test
    void updateAdoption_ThrowsExceptionWithNullValuesPassedThroughParameter() {

        assertThrows(NullPointerException.class,
                () -> adoptionService.updateAdoption(null));
    }


    @Test
    public void deleteAdoption_ReturnFalseIfIncorrectIdWasPassed(){
        assertFalse(adoptionService.deleteAdoption(-1));
    }

    @Test
    public void deleteAdoption_ReturnTrueIfCorrectKeyIsPassed(){

        var idSetup  =
                adoptionService
                        .addNewAdoption(adoptionToAdd)
                        .getId();

        var sizeBeforeDeletion =
                adoptionService.findAllAdoptions().size();
        assertTrue(adoptionService.deleteAdoption(idSetup));

        var sizeAfterDeletion =
                adoptionService.findAllAdoptions().size();

        assertEquals(sizeBeforeDeletion, sizeAfterDeletion + 1);
    }


    @Test
    public void findAdoptionById_ReturnsObjectWithCorrectId(){

        var idSetup = adoptionService
                            .addNewAdoption(adoptionToAdd)
                            .getId();

        assertNotNull(adoptionService.findAdoptionById(idSetup));
    }

    @Test
    public void findAdoptionById_ReturnsNullWithIncorrectId(){
        var idThatWillNeverBeCreated = -1;
        assertNull(adoptionService.findAdoptionById(
                idThatWillNeverBeCreated));
    }

    @Test
    public void getAdopterByName_ReturnCorrectResult() {
        adoptionService.addNewAdoption(adoptionToAdd);
        var allAdoptionsDoneByAntonio = adoptionService.getAdopterBy(
                adopter -> adopter.getAdopterName().equalsIgnoreCase("Antonio Nazco"));

        assertThat(
                allAdoptionsDoneByAntonio,
                Matchers.containsInAnyOrder(adoptionToAdd),
                Matchers.iterableWithSize(1));
    }

    private void assertThat(List<Adopter> allAdoptionsDoneByAntonio,
                            Matcher<Iterable<? extends Adoption>> iterableMatcher,
                            Matcher<Iterable<Object>> hasSize) {
        iterableMatcher.matches(allAdoptionsDoneByAntonio);
        hasSize.matches(allAdoptionsDoneByAntonio);
    }


    @Test
    public void getAdoptersByName_ReturnsAllElementThatContainsName(){

        adoptionService.addNewAdoption(TestFactory.getAdoptionInstance());
        adoptionService.addNewAdoption(TestFactory.getAdoptionInstance());
        adoptionService.addNewAdoption(TestFactory.getAdoptionInstance());

        var adopter = TestFactory.getAdopterInstance();
        var adopterName = adopter.getAdopterName();

        var adopterList = adoptionService.getAdoptersByName(adopterName);
        assertEquals(3, adopterList.size());
    }

    @Test
    public void getAdoptersByName_ReturnsEmptyWhereNameIsNotFound(){

        adoptionService.addNewAdoption(TestFactory.getAdoptionInstance());
        adoptionService.addNewAdoption(TestFactory.getAdoptionInstance());
        adoptionService.addNewAdoption(TestFactory.getAdoptionInstance());

        var adopterList = adoptionService.getAdoptersByName("SQL");
        assertEquals(0, adopterList.size());
    }

}
