package com.expeditors.adoption.dao.jpa;

import com.expeditors.adoption.dao.BaseDao;
import com.expeditors.adoption.dao.repository.AdopterRepo;
import com.expeditors.adoption.domain.entities.Adopter;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Profile("JPA")
public class AnilAdopterRepoAdapter implements BaseDao<Adopter> {

   private final AdopterRepo adopterRepo;

   public AnilAdopterRepoAdapter(AdopterRepo adopterRepo) {
      this.adopterRepo = adopterRepo;
   }

   @Override
   public List<Adopter> findAll() {
      List<Adopter> adopters = adopterRepo.findAll();
      return adopters;
   }

   @Override
   public Adopter findById(int id) {
      return adopterRepo.findById(id).orElse(null);
   }

   @Override
   public Adopter insert(Adopter adopter) {
      adopter = adopterRepo.save(adopter);
      return adopter;
   }

   @Override
   public boolean update(Adopter adopter) {
      adopterRepo.save(adopter);
      return true;
   }

   @Override
   public boolean delete(int i) {
      adopterRepo.deleteById(i);
      return true;
   }
}
