package com.expeditors.adoption.dao.jpa;

import com.expeditors.adoption.domain.entities.Adopter;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static java.lang.System.out;

@SpringBootTest
public class AdopterJPADaosTest {

   @Autowired
   private AnilAdopterJpaDao adopterDao;

   @Autowired
   private AnilAdopterRepoAdapter adopterRepo;

   @Test
   public void testGetAllWithAdopterDAO() {
      List<Adopter> adopters = adopterDao.findAll();

      adopters.forEach(out::println);
   }
}
