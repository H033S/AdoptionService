package com.expeditors.adoption.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JDBC;
import static com.expeditors.adoption.dao.utils.profiles.Profiles.JDBC_TEST;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@Configuration
@Profile(JDBC_TEST)
public class JDBCConfigTest {

    @Bean("connectionForJDBCTest")
    @Primary
    public DataSource getConnectionTest() {

        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
//                .addScript("/sql/h2/0-schema.sql")
//                .addScript("/sql/h2/1-test-data.sql")
                .build();
    }

}
