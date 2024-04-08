
package com.expeditors.adoption.service;

import com.expeditors.adoption.dao.CrudDAO;
import com.expeditors.adoption.domain.entities.Adopter;
import com.expeditors.adoption.domain.entities.Adoption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Service
public class AdoptionService {

    private final CrudDAO<Adoption> adoptionDAO;

    @Autowired
    public AdoptionService(CrudDAO<Adoption> adoptionDAO) {
        this.adoptionDAO = adoptionDAO;
    }

    public Adoption addNewAdoption(Adoption adoption) {
        Objects.requireNonNull(adoption);

        return adoptionDAO.insert(adoption);
    }
    public boolean updateAdoption(Adoption adoption) {
        return adoptionDAO.update(adoption);
    }
    public boolean deleteAdoption(int id) {
        return adoptionDAO.delete(id);
    }

    public Adoption findAdoptionById(int id) {
        return adoptionDAO.findById(id);
    }

    public List<Adoption> findAllAdoptions() {
        return adoptionDAO.findAll();
    }
    public List<Adopter> getAdoptersSortedBy(Comparator<Adoption> comparator) {
        return adoptionDAO.findAll().stream()
                .sorted(comparator)
                .map(Adoption::getAdopter)
                .toList();
    }
    public List<Adopter> getAdoptersSortedByDateOfAdoption() {
        return getAdoptersSortedBy(Comparator.comparing(Adoption::getAdoptionDate));
    }

    public List<Adopter> getAdopterBy(Predicate<Adopter> adopterPredicate) {
        return adoptionDAO.findAll().stream()
                .map(Adoption::getAdopter)
                .filter(adopterPredicate)
                .toList();
    }

    public List<Adopter> getAdoptersByName(String name) {
        return getAdopterBy(
                adopter -> adopter.getAdopterName()
                                                   .equalsIgnoreCase(name));
    }

}