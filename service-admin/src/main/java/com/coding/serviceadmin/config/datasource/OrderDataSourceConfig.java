package com.coding.serviceadmin.config.datasource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "orderEntityManagerFactory",
    transactionManagerRef = "orderTransactionManager",
    basePackages = {"com.coding.supermarket.domain.order"}
)
public class OrderDataSourceConfig {

    @Bean(name = "orderDataSource")
    @ConfigurationProperties(prefix = "spring.order.datasource")
    public DataSource orderDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "orderEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
        EntityManagerFactoryBuilder builder) {
        return builder.dataSource(orderDataSource())
            .packages("com.coding.supermarket.domain.order") // 设置实体类所在位置
            .persistenceUnit("orderPersistenceUnit").build();
    }

    @Bean(name = "orderEntityManagerFactory")
    public EntityManagerFactory entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryBean(builder).getObject();
    }

    @Bean(name = "orderTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("orderEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
