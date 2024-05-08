package com.expeditors.adoption.configs;

import com.expeditors.adoption.dao.utils.profiles.Profiles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JDBC;
import static com.expeditors.adoption.dao.utils.profiles.Profiles.JDBC_TEST;

@Configuration
@Profile("!" + JDBC_TEST)
public class JDBCConfig {

    @Bean("connectionForJDBC")
    @Profile(JDBC)
    public DataSource getConnection(
            @Value(value = "${expeditors.db.connection}") String dbUrl,
            @Value(value = "${expeditors.db.username}") String dbUser,
            @Value(value = "${expeditors.db.password}") String dbPassword) {

        return new DriverManagerDataSource(dbUrl, dbUser, dbPassword);
    }
}
