package com.expeditors.adoption.services.adopter;

import com.expeditors.adoption.dao.BaseDao;
import com.expeditors.adoption.domain.entities.Adopter;
import com.expeditors.adoption.factory.TestFactory;
import com.expeditors.adoption.service.implementation.AdopterServiceImpl;
import com.expeditors.adoption.services.BaseServiceUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class AdopterServiceUnitTest
        extends BaseServiceUnitTest<Adopter> {

    @Mock
    private BaseDao<Adopter> adopterDAO;
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
