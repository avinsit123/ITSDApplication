package com.io.itsd.config;

import com.io.itsd.model.Request;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

@Configuration
@PropertySource("classpath:hibernate.properties")
public class HibernateConfig {

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.connection.driver_class}")
    private String driverClass;

    @Value("${hibernate.connection.url}")
    private String connectionUrl;

    @Value("${hibernate.connection.username}")
    private String username;

    @Value("${hibernate.connection.password}")
    private String password;

    @Bean
    public SessionFactory sessionFactory() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.connection.driver_class", driverClass);
        properties.setProperty("hibernate.connection.url", connectionUrl);
        properties.setProperty("hibernate.connection.username", username);
        properties.setProperty("hibernate.connection.password", password);

        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();

        // todo: Add All Entity Classes to annotated class.
        return cfg.setProperties(properties)
                .addAnnotatedClass(Request.class)
                .buildSessionFactory();
    }

}
