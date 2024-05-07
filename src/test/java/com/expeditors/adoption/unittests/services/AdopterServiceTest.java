package com.expeditors.adoption.unittests.services;

import com.expeditors.adoption.dao.BaseDAO;
import com.expeditors.adoption.domain.entities.Adopter;
import com.expeditors.adoption.factory.TestFactory;
import com.expeditors.adoption.service.BaseService;
import com.expeditors.adoption.service.implementation.AdopterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class AdopterServiceTest
        extends BaseServiceTest<Adopter> {

    @Mock
    private BaseDAO<Adopter> adopterDAO;
    @InjectMocks
    private AdopterServiceImpl adopterService;


    @Override
    @BeforeEach
    public void init() {
        baseDAO = adopterDAO;
        baseService = adopterService;
        mockEntity = TestFactory.getAdopterInstance();
    }
}
