package com.expeditors.adoption.services;

import com.expeditors.adoption.dao.BaseDAO;
import com.expeditors.adoption.domain.entities.Pet;
import com.expeditors.adoption.factory.TestFactory;
import com.expeditors.adoption.service.implementation.PetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class PetServiceUnitTest
        extends BaseServiceUnitTest<Pet> {

    @Mock
    private BaseDAO<Pet> adopterDAO;
    @InjectMocks
    private PetServiceImpl adopterService;


    @Override
    @BeforeEach
    public void init() {
        baseDAO = adopterDAO;
        baseService = adopterService;
        mockEntity = TestFactory.getPetInstance();
    }

}
