package com.expeditors.adoption.services.pet;

import com.expeditors.adoption.dao.BaseDao;
import com.expeditors.adoption.domain.entities.Pet;
import com.expeditors.adoption.factory.TestFactory;
import com.expeditors.adoption.service.implementation.PetServiceImpl;
import com.expeditors.adoption.services.BaseServiceUnitTest;
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
    private BaseDao<Pet> adopterDAO;
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
