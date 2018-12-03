package com.coding.serviceadmin.config.datasource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "baseEntityManagerFactory",
    transactionManagerRef = "baseTransactionManager",
    basePackages = {"com.coding.commons.domain"} // 设置repository所在包
)
public class BaseDataSourceConfig {

    @Primary
    @Bean(name = "baseDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.base")
    public DataSource baseDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "baseEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(baseDataSource())
            .packages("com.coding.commons.domain") // 设置实体类所在位置
            .persistenceUnit("basePersistenceUnit").build();
    }

    @Primary
    @Bean(name = "baseTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("baseEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
