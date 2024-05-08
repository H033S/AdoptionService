package com.expeditors.adoption.dao.jdbc.tryoption.usingtemplate;

import com.expeditors.adoption.dao.BaseDAO;
import com.expeditors.adoption.dao.jdbc.tryoption.usingtemplate.templates.*;
import com.expeditors.adoption.domain.entities.Adopter;
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
public class AdopterJdbcDao
        implements BaseDAO<Adopter> {

    private final DataSource datasource;

    public AdopterJdbcDao(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public List<Adopter> findAll() {

        String sql = """
        SELECT ID, NAME, PHONE_NUMBER FROM ADOPTER
        """;

        JdbcListTemplate<Adopter> template = new JdbcListTemplate<>(datasource) {
            @Override
            public Adopter mapItem(ResultSet rSet) throws SQLException {
                return new Adopter(
                        rSet.getInt("id"),
                        rSet.getString("name"),
                        rSet.getString("phone_number")
                );
            }
        };

        return template.findAll(sql);
    }

    @Override
    public Adopter findById(int id) {

        String sql = """
        SELECT ID, NAME, PHONE_NUMBER FROM ADOPTER
        WHERE ID = ?
        """;

        JdbcFindByIdTemplate<Adopter> template = new JdbcFindByIdTemplate<>(datasource) {
            @Override
            public Adopter mapItem(ResultSet rSet) throws SQLException {
                return new Adopter(
                        rSet.getInt("id"),
                        rSet.getString("name"),
                        rSet.getString("phone_number")
                );
            }
        };

        return template.findById(id, sql);
    }

    @Override
    public Adopter insert(Adopter adopter) {

        String sql = """
        INSERT INTO ADOPTER (NAME, PHONE_NUMBER)
        VALUES (?, ?)
        """;

        JdbcInsertTemplate<Adopter> template = new JdbcInsertTemplate<>(datasource) {
            @Override
            public void prepareStatement(
                    PreparedStatement pStmt,
                    Adopter adopterToInsert) throws SQLException {

                pStmt.setString(1, adopterToInsert.getAdopterName());
                pStmt.setString(2, adopterToInsert.getPhoneNumber());
            }
        };
        return template.insert(adopter, sql);
    }

    @Override
    public boolean update(Adopter adopter) {

        String sql = """
        UPDATE ADOPTER SET
            NAME = ?,
            PHONE_NUMBER = ?
        WHERE ID = ?
        """;

        JdbcUpdateTemplate<Adopter> template = new JdbcUpdateTemplate<>(datasource) {
            @Override
            public void prepareStatement(
                    PreparedStatement pStmt,
                    Adopter adopterToUpdate) throws SQLException {

                pStmt.setString(1, adopterToUpdate.getAdopterName());
                pStmt.setString(2, adopterToUpdate.getPhoneNumber());
                pStmt.setInt(3, adopterToUpdate.getId());
            }
        };
        return template.update(adopter, sql);
    }

    @Override
    public boolean delete(int id) {

        String sql = """
        DELETE FROM ADOPTER WHERE ID = ?
        """;
        JdbcDeleteTemplate template = new JdbcDeleteTemplate(datasource) {};
        return template.delete(id, sql);
    }
}
