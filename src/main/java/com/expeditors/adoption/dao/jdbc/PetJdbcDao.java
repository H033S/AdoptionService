package com.expeditors.adoption.dao.jdbc;

import com.expeditors.adoption.dao.BaseDAO;
import com.expeditors.adoption.domain.entities.Pet;

import java.util.List;

public class PetJdbcDao implements BaseDAO<Pet> {

    @Override
    public Pet insert(Pet pet) {
        return null;
    }

    @Override
    public boolean update(Pet pet) {
        return false;
    }

    @Override
    public boolean delete(int i) {
        return false;
    }

    @Override
    public Pet findById(int i) {
        return null;
    }

    @Override
    public List<Pet> findAll() {
        return null;
    }
}
