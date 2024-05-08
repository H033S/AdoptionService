package com.expeditors.adoption.dao.jdbc.tryoption.usingtemplate.templates;

import com.expeditors.adoption.domain.Entity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class JdbcListTemplate<TEntity extends Entity>
        extends JdbcAbstractBaseTemplate {

    public JdbcListTemplate(DataSource source) {
        super(source);
    }

    public abstract TEntity mapItem(ResultSet rSet) throws SQLException;

    public List<TEntity> findAll(String sql){
        List<TEntity> entities = new ArrayList<>();

        try(
                Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rSet = stmt.executeQuery(sql);
        ) {

            while (rSet.next()){
                TEntity entity = mapItem(rSet);
                entities.add(entity);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entities;
    }
}
