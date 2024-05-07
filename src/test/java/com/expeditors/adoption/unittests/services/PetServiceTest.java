package com.expeditors.adoption.unittests.services;

import com.expeditors.adoption.dao.BaseDAO;
import com.expeditors.adoption.domain.entities.Adopter;
import com.expeditors.adoption.domain.entities.Pet;
import com.expeditors.adoption.factory.TestFactory;
import com.expeditors.adoption.service.BaseService;
import com.expeditors.adoption.service.PetService;
import com.expeditors.adoption.service.implementation.AdopterServiceImpl;
import com.expeditors.adoption.service.implementation.PetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest extends BaseServiceTest<Pet> {

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
