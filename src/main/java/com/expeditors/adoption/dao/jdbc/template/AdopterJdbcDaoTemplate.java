package com.expeditors.adoption.dao.jdbc.template;

import com.expeditors.adoption.dao.BaseDao;
import com.expeditors.adoption.dao.jdbc.AdopterSqlQueries;
import com.expeditors.adoption.dao.jdbc.PetSqlQueries;
import com.expeditors.adoption.domain.entities.Adopter;
import com.expeditors.adoption.domain.entities.Pet;
import com.expeditors.adoption.domain.entities.PetBreed;
import com.expeditors.adoption.domain.entities.PetType;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JDBC_TEMPLATE;
import static com.expeditors.adoption.dao.utils.profiles.Profiles.JDBC_TEMPLATE_TEST;

@Repository
@Profile({JDBC_TEMPLATE, JDBC_TEMPLATE_TEST})
public class AdopterJdbcDaoTemplate
        implements BaseDao<Adopter> {

    private final DataSource dataSource;

    public AdopterJdbcDaoTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Adopter> findAll() {

        JdbcTemplate template = new JdbcTemplate(dataSource);
        return template.query(
                AdopterSqlQueries.getFindAllQuery(),
                new RowMapper<Adopter>() {
                    @Override
                    public Adopter mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Adopter(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("phone_number")
                        );
                    }
                });
    }

    @Override
    public Adopter findById(int id) {

        JdbcTemplate template = new JdbcTemplate(dataSource);
        return template.queryForObject(
                AdopterSqlQueries.getFindByIdQuery(),
                new Object[]{},
                new int[]{id},
                new RowMapper<Adopter>() {
                    @Override
                    public Adopter mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Adopter(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("phone_number")
                        );
                    }
                });
    }

    @Override
    public Adopter insert(Adopter adopter) {

        JdbcTemplate template = new JdbcTemplate(dataSource);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update((con) -> {
                PreparedStatement pStmt = con.prepareStatement(
                        AdopterSqlQueries.getInsertQuery(),
                        PreparedStatement.RETURN_GENERATED_KEYS);

                pStmt.setString(1, adopter.getAdopterName());
                pStmt.setString(2, adopter.getPhoneNumber());
                return pStmt;
                },
                keyHolder
        );

        if(Objects.isNull(keyHolder.getKey())){
            return null;
        }
        adopter.setId(keyHolder.getKey().intValue());
        return adopter;

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
