package com.expeditors.adoption.dao.jdbc.tryoption.usingtemplate.templates;

import com.expeditors.adoption.domain.Entity;

import javax.sql.DataSource;
import java.sql.*;

public abstract class JdbcInsertTemplate<TEntity extends Entity>
        extends JdbcAbstractBaseTemplate{

    public JdbcInsertTemplate(DataSource source) {
        super(source);
    }

    public TEntity insert(TEntity entity, String sql){
        try(
                Connection conn = getConnection();
                PreparedStatement pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {

            prepareStatement(pStmt, entity);
            pStmt.executeUpdate();

            try(ResultSet rSet = pStmt.getGeneratedKeys()){
                rSet.next();
                entity.setId(rSet.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entity;
    }

    public abstract void prepareStatement(PreparedStatement pStmt, TEntity entity) throws SQLException;
}
