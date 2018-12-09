package com.coding.serviceadmin.config.datasource;

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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "couponEntityManagerFactory",
    transactionManagerRef = "couponTransactionManager",
    basePackages = {"com.coding.supermarket.domain.coupon"}
)
public class CouponDataSourceConfig {
    @Inject
    private JpaProperties jpaProperties;

    @Bean(name = "couponDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.coupon")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "couponDataSource")
    public DataSource couponDataSource() {
        return dataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "couponEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(couponDataSource()).properties(jpaProperties.getHibernateProperties(HibernateSettingsFactory.getHibernateSettings(jpaProperties)))
            .packages("com.coding.supermarket.domain.coupon") // 设置实体类所在位置
            .persistenceUnit("couponPersistenceUnit").build();
    }

    @Bean(name = "couponTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("couponEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
