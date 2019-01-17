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
    entityManagerFactoryRef = "couponEntityManagerFactory",
    basePackages = {"com.coding.supermarket.domain.coupon"}
)
public class CouponDataSourceConfig {

    @Bean(name = "couponDataSource")
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.coupon")
    public DataSource couponDataSource() {
        return new AtomikosDataSourceBean();
    }

    @Bean(name = "couponEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
                                                                           JpaProperties jpaProperties) {
        return builder.dataSource(couponDataSource())
                      .properties(HibernateProperties.from(jpaProperties))
                      .jta(true)
                      .packages("com.coding.supermarket.domain.coupon")
                      .persistenceUnit("couponPersistenceUnit")
                      .build();
    }

}
