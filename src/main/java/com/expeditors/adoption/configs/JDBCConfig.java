package com.expeditors.adoption.configs;

import com.expeditors.adoption.dao.BaseDAO;
import com.expeditors.adoption.dao.jdbc.AdopterJdbcDao;
import com.expeditors.adoption.dao.utils.profiles.Profiles;
import com.expeditors.adoption.domain.entities.Adopter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConfig {
    
    @Bean
    @Profile(Profiles.JDBC)
    public BaseDAO<Adopter> getAdopterDAO(DataSource source){
        return new AdopterJdbcDao(source);
    }

    @Bean
    public Connection getConnection(
            @Value(value = "${expeditors.db.connection}") String dbUrl,
            @Value(value = "${expeditors.db.username}") String dbUser,
            @Value(value = "${expeditors.db.password}") String dbPassword) throws SQLException {

        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

}
