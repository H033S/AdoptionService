
package com.expeditors.adoption.service.implementation;

import com.expeditors.adoption.dao.CrudDAO;
import com.expeditors.adoption.domain.entities.Adopter;
import com.expeditors.adoption.domain.entities.Adoption;
import com.expeditors.adoption.service.AdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Service
public class AdoptionServiceImpl implements AdoptionService {

    private final CrudDAO<Adoption> adoptionDAO;

    @Autowired
    public AdoptionServiceImpl(CrudDAO<Adoption> adoptionDAO) {
        this.adoptionDAO = adoptionDAO;
    }

    @Override
    public Adoption addNewAdoption(Adoption adoption) {
        if(Objects.isNull(adoption)){
            throw new IllegalArgumentException(
                    "Adoption cannot be null");
        }

        return adoptionDAO.insert(adoption);
    }
    @Override
    public boolean updateAdoption(Adoption adoption) {
        if(Objects.isNull(adoption)){
            throw new IllegalArgumentException(
                    "Adoption cannot be null");
        }

        return adoptionDAO.update(adoption);
    }

    @Override
    public boolean deleteAdoption(int id) {
        return adoptionDAO.delete(id);
    }

    @Override
    public Adoption findAdoptionById(int id) {
        return adoptionDAO.findById(id);
    }

    @Override
    public List<Adoption> findAllAdoptions() {
        return adoptionDAO.findAll();
    }

    @Override
    public List<Adopter> getAdoptersSortedBy(Comparator<Adoption> comparator) {
        return adoptionDAO.findAll().stream()
                .sorted(comparator)
                .map(Adoption::getAdopter)
                .toList();
    }

    @Override
    public List<Adopter> getAdoptersSortedByDateOfAdoption() {
        return getAdoptersSortedBy(Comparator.comparing(Adoption::getAdoptionDate));
    }

    @Override
    public List<Adopter> getAdopterBy(Predicate<Adoption> adopterPredicate) {
        return adoptionDAO.findAll().stream()
                .filter(adopterPredicate)
                .map(Adoption::getAdopter)
                .toList();
    }

    @Override
    public List<Adopter> getAdoptersByName(String name) {
        return getAdopterBy(adoption -> adoption
                .getAdopter()
                .getAdopterName()
                .equalsIgnoreCase(name));
    }

}