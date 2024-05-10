package com.expeditors.adoption.dao.jdbc.template;

import com.expeditors.adoption.dao.BaseDao;
import com.expeditors.adoption.dao.jdbc.PetSqlQueries;
import com.expeditors.adoption.domain.entities.Adopter;
import com.expeditors.adoption.domain.entities.Pet;
import com.expeditors.adoption.domain.entities.PetBreed;
import com.expeditors.adoption.domain.entities.PetType;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JDBC_TEMPLATE;
import static com.expeditors.adoption.dao.utils.profiles.Profiles.JDBC_TEMPLATE_TEST;

@Repository
@Profile({JDBC_TEMPLATE, JDBC_TEMPLATE_TEST})
public class AdopterJdbcDaoTemplate
        implements BaseDao<Adopter> {

    @Override
    public List<Adopter> findAll() {
        return null;
    }

    @Override
    public Adopter findById(int id) {
        return null;
    }

    @Override
    public Adopter insert(Adopter adopter) {
        return null;
    }

    @Override
    public boolean update(Adopter adopter) {
        return false;
    }

    @Override
    public boolean delete(int i) {
        return false;
    }
}
