package com.coding.supermarket.serviceadmin.config.datasource;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@DependsOn("transactionManager")
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "orderEntityManagerFactory",
    basePackages = {"com.coding.supermarket.domain.order"}
)
public class OrderDataSourceConfig {

    @Bean(name = "orderDataSource")
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.order")
    public DataSource orderDataSource() {
        return new AtomikosDataSourceBean();
    }

    @Bean(name = "orderEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
                                                                           JpaProperties jpaProperties) {
        return builder.dataSource(orderDataSource())
                      .properties(HibernateProperties.from(jpaProperties))
                      .jta(true)
                      .packages("com.coding.supermarket.domain.order")
                      .persistenceUnit("orderPersistenceUnit").build();
    }

}
