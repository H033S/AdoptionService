package com.expeditors.adoption.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.*;

@Configuration
@Profile({JDBC, JDBC_TEMPLATE, JPA})
public class DataSourceConfig {

    @Bean
    public DataSource getConnection(
            @Value(value = "${expeditors.db.connection}") String dbUrl,
            @Value(value = "${expeditors.db.username}") String dbUser,
            @Value(value = "${expeditors.db.password}") String dbPassword) {

        DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        managerDataSource.setUrl(dbUrl);
        managerDataSource.setUsername(dbUser);
        managerDataSource.setPassword(dbPassword);
        managerDataSource.setDriverClassName("org.postgresql.Driver");
        return managerDataSource;
    }

}
