package com.coding.supermarket.serviceadmin.config.datasource;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@DependsOn("transactionManager")
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "baseEntityManagerFactory",
    basePackages = {"com.coding.commons.domain"} // 设置repository所在包
)
public class BaseDataSourceConfig {

    @Primary
    @Bean(name = "baseDataSource")
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.base")
    public DataSource baseDataSource() {
        return new AtomikosDataSourceBean();
    }

    @Primary
    @Bean(name = "baseEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
                                                                           JpaProperties jpaProperties) {
        return builder.dataSource(baseDataSource())
                      .properties(HibernateProperties.from(jpaProperties))
                      .jta(true)
                      .packages("com.coding.commons.domain") // 设置实体类所在位置
                      .persistenceUnit("basePersistenceUnit")
                      .build();
    }

}
