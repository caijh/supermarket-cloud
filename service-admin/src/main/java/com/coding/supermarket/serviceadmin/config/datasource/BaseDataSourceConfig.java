package com.coding.supermarket.serviceadmin.config.datasource;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

    @Inject
    private JpaProperties jpaProperties;

    @Primary
    @Bean(name = "baseDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.base")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "baseDataSource")
    public DataSource baseDataSource() {
        return dataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name = "baseEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(baseDataSource()).properties(jpaProperties.getHibernateProperties(HibernateSettingsFactory.getHibernateSettings(jpaProperties)))
            .packages("com.coding.commons.domain") // 设置实体类所在位置
            .persistenceUnit("basePersistenceUnit").build();
    }

    @Primary
    @Bean(name = "baseTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("baseEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
