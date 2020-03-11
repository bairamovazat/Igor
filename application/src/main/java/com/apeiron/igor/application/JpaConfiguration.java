package com.apeiron.igor.application;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.apeiron.igor.repository")
@EntityScan(basePackages = "com.apeiron.igor.model")
public class JpaConfiguration {

    @Bean(name = "transactionManager")
    public PlatformTransactionManager testTransactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
