package com.expeditors.adoption.dao.jdbc;

import com.expeditors.adoption.dao.BaseDAO;
import com.expeditors.adoption.domain.entities.Adopter;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdopterJdbcDao
        implements BaseDAO<Adopter> {

    private final DataSource source;

    public AdopterJdbcDao( DataSource source) {
        this.source = source;
    }

    private Connection getConnection() throws SQLException {
        return source.getConnection();
    }

    @Override
    public Adopter insert(Adopter adopter) {

        String sql = """
        INSERT INTO ADOPTAPP.PUBLIC.ADOPTER (NAME, PHONE_NUMBER)
        VALUES (?, ?)
        """;

        try(
                Connection conn = getConnection();
                PreparedStatement pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
                ) {

            pStmt.setString(1, adopter.getAdopterName());
            pStmt.setString(2, adopter.getPhoneNumber());
            pStmt.executeUpdate();

            try(ResultSet rSet = pStmt.getGeneratedKeys()){
                rSet.next();
                adopter.setId(rSet.getInt(rSet.getInt(1)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return adopter;
    }

    @Override
    public boolean update(Adopter adopter) {

        String sql = """
        UPDATE ADOPTAPP.PUBLIC.ADOPTER SET
            NAME = ?,
            PHONE_NUMBER = ?
        WHERE ID = ?
        """;
        int numberOfRowsUpdated = 0;

        try(
                Connection conn = getConnection();
                PreparedStatement pStmt = conn.prepareStatement(sql);
                ) {
            pStmt.setString(1, adopter.getAdopterName());
            pStmt.setString(2, adopter.getPhoneNumber());
            pStmt.setInt(3, adopter.getId());
            numberOfRowsUpdated = pStmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return numberOfRowsUpdated > 0;
    }

    @Override
    public Adopter findById(int id) {

        String sql = """
        SELECT ID, NAME, PHONE_NUMBER FROM ADOPTAPP.PUBLIC.ADOPTER
        WHERE ID = ?
        """;
        Adopter adopter = null;

        try (
                Connection conn = getConnection();
                PreparedStatement pStmt = conn.prepareStatement(sql);
                ) {
            pStmt.setInt(1, id);

            try(ResultSet rSet = pStmt.executeQuery()){

                rSet.next();
                adopter = new Adopter(
                        rSet.getInt("id"),
                        rSet.getString("name"),
                        rSet.getString("phone_number")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return adopter;
    }

    @Override
    public List<Adopter> findAll() {
        String sql = """
        SELECT ID, NAME, PHONE_NUMBER FROM ADOPTAPP.PUBLIC.ADOPTER
        """;
        List<Adopter> adopters = new ArrayList<>();

        try(
                Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rSet = stmt.executeQuery(sql);
                ) {

            while (rSet.next()){
                var adopter = new Adopter(
                        rSet.getInt("id"),
                        rSet.getString("name"),
                        rSet.getString("phone_number")
                );
                adopters.add(adopter);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return adopters;
    }

    @Override
    public boolean delete(int i) {

        String sql = """
        DELETE FROM ADOPTAPP.PUBLIC.ADOPTER WHERE ID = ?
        """;
        int numberOfRowsDeleted = 0;

        try(
                Connection conn = getConnection();
                PreparedStatement pStmt = conn.prepareStatement(sql);
                ){
            numberOfRowsDeleted = pStmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return numberOfRowsDeleted > 0;
    }
}
