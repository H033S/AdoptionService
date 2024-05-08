package com.expeditors.adoption.dao.jdbc.tryoption.usingtemplate;

import com.expeditors.adoption.dao.BaseDAO;
import com.expeditors.adoption.dao.jdbc.tryoption.usingtemplate.templates.*;
import com.expeditors.adoption.domain.entities.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JDBC;
import static com.expeditors.adoption.dao.utils.profiles.Profiles.JDBC_TEST;

@Repository
@Profile({JDBC, JDBC_TEST})
public class AdoptionJdbcDao
        implements BaseDAO<Adoption> {
    private final DataSource dataSource;

    public AdoptionJdbcDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Adoption> findAll() {

        String sql = """
                SELECT
                    ADOPTER.ID              AS "ADOPTER_ID",
                    ADOPTER.NAME            AS "ADOPTER_NAME",
                    ADOPTER.PHONE_NUMBER    AS "ADOPTER_PHONE_NUMBER",
                    PET.ID                  AS "PET_ID",
                    PET.NAME                AS "PET_NAME",
                    PET.TYPE                AS "PET_TYPE",
                    PET.BREED               AS "PET_BREED",
                    ADOPTION.ID             AS "ADOPTION_ID",
                    ADOPTION.ADOPTION_DATE  AS "ADOPTION_DATE"
                FROM ADOPTION
                    INNER JOIN ADOPTER ON ADOPTER.ID = ADOPTION.ADOPTER_ID
                    INNER JOIN PET ON PET.ID = ADOPTION.PET_ID
                """;
        JdbcListTemplate<Adoption> template = new JdbcListTemplate<Adoption>(dataSource) {
            @Override
            public Adoption mapItem(ResultSet rSet) throws SQLException {
                return createAdoptionFromResultSet(rSet);
            }
        };

        return template.findAll(sql);
    }

    @Override
    public Adoption findById(int adoptionId) {

        String sql = """
                SELECT
                    ADOPTER.ID              AS "ADOPTER_ID",
                    ADOPTER.NAME            AS "ADOPTER_NAME",
                    ADOPTER.PHONE_NUMBER    AS "ADOPTER_PHONE_NUMBER",
                    PET.ID                  AS "PET_ID",
                    PET.NAME                AS "PET_NAME",
                    PET.TYPE                AS "PET_TYPE",
                    PET.BREED               AS "PET_BREED",
                    ADOPTION.ID             AS "ADOPTION_ID",
                    ADOPTION.ADOPTION_DATE  AS "ADOPTION_DATE"
                FROM ADOPTION
                    INNER JOIN ADOPTER ON ADOPTER.ID = ADOPTION.ADOPTER_ID
                    INNER JOIN PET ON PET.ID = ADOPTION.PET_ID
                WHERE ADOPTION.ID = ?
                """;

        JdbcFindByIdTemplate<Adoption> template = new JdbcFindByIdTemplate<>(dataSource) {
            @Override
            public Adoption mapItem(ResultSet rSet) throws SQLException {
                return createAdoptionFromResultSet(rSet);
            }
        };

        return template.findById(adoptionId, sql);
    }

    private static Adoption createAdoptionFromResultSet(ResultSet rSet) throws SQLException {
        return new Adoption(
                rSet.getInt("adoption_id"),
                new Adopter(
                        rSet.getInt("adopter_id"),
                        rSet.getString("adopter_name"),
                        rSet.getString("adopter_phone_number")
                ),
                new Pet(
                        rSet.getInt("pet_id"),
                        PetBreed.valueOf(rSet.getString("pet_breed")),
                        PetType.valueOf(rSet.getString("pet_type")),
                        rSet.getString("pet_name")
                ),
                rSet.getDate("adoption_date").toLocalDate()
        );
    }

    @Override
    public Adoption insert(Adoption adoption) {

        String sql = """
               INSERT INTO ADOPTION (ADOPTION_DATE, PET_ID, ADOPTER_ID)
               VALUES (?, ?, ?)
               """;
        JdbcInsertTemplate<Adoption> template = new JdbcInsertTemplate<>(dataSource) {
            @Override
            public void prepareStatement(
                    PreparedStatement pStmt,
                    Adoption adoptionToInsert) throws SQLException {

                pStmt.setDate(1, Date.valueOf(adoptionToInsert.getAdoptionDate()));
                pStmt.setInt(2, adoptionToInsert.getPet().getId());
                pStmt.setInt(3, adoptionToInsert.getAdopter().getId());
            }
        };

        return template.insert(adoption, sql);
    }

    @Override
    public boolean update(Adoption adoption) {

        String sql = """
               UPDATE ADOPTION SET
                    ADOPTION_DATE = ?,
                    PET_ID = ?,
                    ADOPTER_ID = ?
               WHERE ID = ?
               """;
        JdbcUpdateTemplate<Adoption> template = new JdbcUpdateTemplate<>(dataSource) {
            @Override
            public void prepareStatement(
                    PreparedStatement pStmt,
                    Adoption adoptionToUpdate) throws SQLException {

                pStmt.setDate(1, Date.valueOf(adoptionToUpdate.getAdoptionDate()));
                pStmt.setInt(2, adoptionToUpdate.getPet().getId());
                pStmt.setInt(3, adoptionToUpdate.getAdopter().getId());
                pStmt.setInt(4, adoptionToUpdate.getId());
            }
        };

        return template.update(adoption, sql);
    }

    @Override
    public boolean delete(int id) {

        String sql = """
                DELETE FROM ADOPTION
                WHERE ID = ?
                """;
        JdbcDeleteTemplate template = new JdbcDeleteTemplate(dataSource) {};
        return template.delete(id, sql);
    }
}
