package com.expeditors.adoption.service;

import com.expeditors.adoption.dao.CrudDAO;
import com.expeditors.adoption.domain.entities.Pet;
import com.expeditors.adoption.service.implementation.PetServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

    @Mock
    private CrudDAO<Pet> petDAO;
    @InjectMocks
    private PetServiceImpl petService;

    @Test
    public void getAdopterById_ReturnNull_WhenIdIsNotFound(){
        Mockito.doReturn(null)
                .when(petDAO).findById(-1);

        assertNull(petService.getPetById(-1));
    }
}
