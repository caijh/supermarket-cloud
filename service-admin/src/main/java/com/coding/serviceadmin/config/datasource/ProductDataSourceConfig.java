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
    entityManagerFactoryRef = "productEntityManagerFactory",
    transactionManagerRef = "productTransactionManager",
    basePackages = {"com.coding.supermarket.domain.product"} // 设置repository所在包
)
public class ProductDataSourceConfig {


    @Bean(name = "productDataSource")
    @ConfigurationProperties(prefix = "spring.product.datasource")
    public DataSource productDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "productEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(productDataSource())
            .packages("com.coding.supermarket.domain.product") // 设置实体类所在位置
            .persistenceUnit("productPersistenceUnit").build();
    }

    @Bean(name = "productEntityManagerFactory")
    public EntityManagerFactory entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryBean(builder).getObject();
    }

    @Bean(name = "productTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("productEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
