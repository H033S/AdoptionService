package com.expeditors.adoption.dao.jdbc.template;

import com.expeditors.adoption.dao.BaseDao;
import com.expeditors.adoption.domain.entities.Adoption;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JDBC_TEMPLATE;
import static com.expeditors.adoption.dao.utils.profiles.Profiles.JDBC_TEMPLATE_TEST;

@Repository
@Profile(value = {JDBC_TEMPLATE, JDBC_TEMPLATE_TEST})
public class AdoptionJdbcDaoTemplate
        implements BaseDao<Adoption> {
    @Override
    public List<Adoption> findAll() {
        return null;
    }

    @Override
    public Adoption findById(int id) {
        return null;
    }

    @Override
    public Adoption insert(Adoption adoption) {
        return null;
    }

    @Override
    public boolean update(Adoption adoption) {
        return false;
    }

    @Override
    public boolean delete(int i) {
        return false;
    }
}
