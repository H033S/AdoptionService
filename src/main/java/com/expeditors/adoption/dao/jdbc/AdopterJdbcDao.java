package com.expeditors.adoption.dao.jdbc;

import com.expeditors.adoption.dao.BaseDAO;
import com.expeditors.adoption.dao.jdbc.templates.*;
import com.expeditors.adoption.domain.entities.Adopter;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdopterJdbcDao
        implements BaseDAO<Adopter> {

    private final DataSource datasource;

    public AdopterJdbcDao(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public List<Adopter> findAll() {

        String sql = """
        SELECT ID, NAME, PHONE_NUMBER FROM ADOPT_APP.PUBLIC.ADOPTER
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
        SELECT ID, NAME, PHONE_NUMBER FROM ADOPT_APP.PUBLIC.ADOPTER
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
        INSERT INTO ADOPT_APP.PUBLIC.ADOPTER (NAME, PHONE_NUMBER)
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
        UPDATE ADOPT_APP.PUBLIC.ADOPTER SET
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
        DELETE FROM ADOPT_APP.PUBLIC.ADOPTER WHERE ID = ?
        """;

        JdbcDeleteTemplate template = new JdbcDeleteTemplate(datasource) {
            @Override
            public boolean delete(int id, String sql) {
                return super.delete(id, sql);
            }
        };
        return template.delete(id, sql);
    }
}
