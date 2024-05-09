package com.expeditors.adoption.dao.jdbc.tryoption.usingtemplate;

import com.expeditors.adoption.dao.BaseDAO;
import com.expeditors.adoption.dao.jdbc.tryoption.usingtemplate.templates.*;
import com.expeditors.adoption.domain.entities.Pet;
import com.expeditors.adoption.domain.entities.PetBreed;
import com.expeditors.adoption.domain.entities.PetType;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JDBC;
import static com.expeditors.adoption.dao.utils.profiles.Profiles.JDBC_TEST;

@Repository
@Profile({JDBC, JDBC_TEST})
public class PetJdbcDao implements BaseDAO<Pet> {

    private final DataSource dataSource;

    public PetJdbcDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Pet> findAll() {

        String sql = """
        SELECT ID, NAME, BREED, TYPE FROM PET
        """;

        JdbcListTemplate<Pet> template = new JdbcListTemplate<>(dataSource) {
            @Override
            public Pet mapItem(ResultSet rSet) throws SQLException {
                return new Pet(
                        rSet.getInt("id"),
                        PetBreed.valueOf(rSet.getString("breed")),
                        PetType.valueOf(rSet.getString("type")),
                        rSet.getString("name")
                );
            }
        };

        return template.findAll(sql);
    }

    @Override
    public Pet findById(int id) {

        String sql = """
        SELECT ID, NAME, BREED, TYPE FROM PET
        WHERE ID = ?
        """;

        JdbcFindByIdTemplate<Pet> template = new JdbcFindByIdTemplate<>(dataSource) {
            @Override
            public Pet mapItem(ResultSet rSet) throws SQLException {
                return new Pet(
                        rSet.getInt("id"),
                        PetBreed.valueOf(rSet.getString("breed")),
                        PetType.valueOf(rSet.getString("type")),
                        rSet.getString("name")
                );
            }
        };

        return template.findById(id, sql);
    }

    @Override
    public Pet insert(Pet pet) {

        String sql = """
        INSERT INTO PET (NAME, BREED, TYPE)
        VALUES (?,  ?, ?)
        """;

        JdbcInsertTemplate<Pet> template = new JdbcInsertTemplate<>(dataSource) {
            @Override
            public void prepareStatement(PreparedStatement pStmt, Pet petToInsert) throws SQLException {
                pStmt.setString(1, petToInsert.getPetName());
                pStmt.setString(2, petToInsert.getPetBreed().name());
                pStmt.setString(3, petToInsert.getPetType().name());
            }
        };

        return template.insert(pet, sql);
    }

    @Override
    public boolean update(Pet pet) {

        String sql = """
                UPDATE PET SET
                    NAME = ?,
                    BREED = ?,
                    TYPE = ?
                WHERE ID = ?
                """;

        JdbcUpdateTemplate<Pet> template = new JdbcUpdateTemplate<>(dataSource) {
            @Override
            public void prepareStatement(PreparedStatement pStmt, Pet petToInsert) throws SQLException {
                pStmt.setString(1, petToInsert.getPetName());
                pStmt.setString(2, petToInsert.getPetBreed().name());
                pStmt.setString(3, petToInsert.getPetType().name());
                pStmt.setInt(4, petToInsert.getId());
            }
        };

        return template.update(pet, sql);
    }

    @Override
    public boolean delete(int id) {
        String sql = """
                DELETE FROM PET
                WHERE ID = ?
                """;
        JdbcDeleteTemplate template = new JdbcDeleteTemplate(dataSource) {};
        return template.delete(id, sql);
    }
}
