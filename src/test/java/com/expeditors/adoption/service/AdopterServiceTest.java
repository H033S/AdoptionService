package com.expeditors.adoption.service;

import com.expeditors.adoption.dao.CrudDAO;
import com.expeditors.adoption.domain.entities.Adopter;
import com.expeditors.adoption.service.implementation.AdopterServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class AdopterServiceTest {

    @Mock
    private CrudDAO<Adopter> adopterDAO;
    @InjectMocks
    private AdopterServiceImpl adopterService;

    @Test
    public void getAdopterById_ReturnNull_WhenIdIsNotFound(){
        Mockito.doReturn(null)
                .when(adopterDAO).findById(-1);

        assertNull(adopterService.getAdopterById(-1));
    }
}
