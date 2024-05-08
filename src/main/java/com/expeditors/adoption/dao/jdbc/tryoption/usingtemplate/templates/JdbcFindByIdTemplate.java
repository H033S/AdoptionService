package com.expeditors.adoption.dao.jdbc.tryoption.usingtemplate.templates;

import com.expeditors.adoption.domain.Entity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class JdbcFindByIdTemplate<TEntity extends Entity>
        extends JdbcAbstractBaseTemplate  {
    public JdbcFindByIdTemplate(DataSource source) {
        super(source);
    }

    public TEntity findById(int id, String sql) {

        TEntity entity = null;

        try (
                Connection conn = getConnection();
                PreparedStatement pStmt = conn.prepareStatement(sql);
        ) {
            pStmt.setInt(1, id);

            try(ResultSet rSet = pStmt.executeQuery()){

                rSet.next();
                entity = mapItem(rSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entity;
    }

    public abstract TEntity mapItem(ResultSet rSet) throws SQLException;
}
