package com.expeditors.adoption.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JPA;

//@Profile(JPA)
//@Configuration
public class JpaConfiguration {



//    @Bean("PUFactory")
//    public EntityManagerFactory getJpaFactory(){
//        return Persistence.createEntityManagerFactory("adoption-app-pu");
//    }
//
//    @Bean("PUManager")
//    public EntityManager getJPAManager(EntityManagerFactory emf){
//        return emf.createEntityManager();
//    }
//
//    @Bean("PUTransaction")
//    public EntityTransaction getTransaction(EntityManager manager){
//        return manager.getTransaction();
//    }
}
