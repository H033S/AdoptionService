package com.expeditors.adoption.configs;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.expeditors.adoption.dao.utils.profiles.Profiles.JPA;
import static com.expeditors.adoption.dao.utils.profiles.Profiles.JPA_TEST;

@Configuration
@Profile({"!" + JPA + "& !" + JPA_TEST})
@EnableAutoConfiguration(
        exclude = {
                DataSourceAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class,
        })
public class NoJPAConfig {
}
