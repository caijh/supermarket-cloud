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
    entityManagerFactoryRef = "productEntityManagerFactory",
    basePackages = {"com.coding.supermarket.domain.product"} // 设置repository所在包
)
public class ProductDataSourceConfig {

    @Bean(name = "productDataSource")
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.product")
    public DataSource productDataSource() {
        return new AtomikosDataSourceBean();
    }

    @Bean(name = "productEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
                                                                           JpaProperties jpaProperties) {
        return builder.dataSource(productDataSource())
                      .properties(HibernateProperties.from(jpaProperties))
                      .jta(true)
                      .packages("com.coding.supermarket.domain.product")
                      .persistenceUnit("productPersistenceUnit")
                      .build();
    }

}
