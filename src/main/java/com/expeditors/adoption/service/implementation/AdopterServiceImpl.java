package com.expeditors.adoption.service.implementation;

import com.expeditors.adoption.dao.BaseDao;
import com.expeditors.adoption.domain.entities.Adopter;
import com.expeditors.adoption.service.AdopterService;
import org.springframework.stereotype.Service;

@Service
public class AdopterServiceImpl
        extends AbstractBaseService<Adopter>
        implements AdopterService {

    public AdopterServiceImpl(BaseDao<Adopter> adopterDAO) {
        super(adopterDAO);
    }

}
