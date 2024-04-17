package com.expeditors.adoption.service.implementation;

import com.expeditors.adoption.dao.CrudDAO;
import com.expeditors.adoption.domain.entities.Adopter;
import com.expeditors.adoption.service.AdopterService;
import org.springframework.stereotype.Service;

@Service
public class AdopterServiceImpl implements AdopterService {

    private final CrudDAO<Adopter> adopterDAO;

    public AdopterServiceImpl(CrudDAO<Adopter> adopterRepository) {
        this.adopterDAO = adopterRepository;
    }

    @Override
    public Adopter getAdopterById(int adopterId) {
        return adopterDAO.findById(adopterId);
    }
}
