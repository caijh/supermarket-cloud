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
    entityManagerFactoryRef = "couponEntityManagerFactory",
    transactionManagerRef = "couponTransactionManager",
    basePackages = {"com.coding.supermarket.domain.coupon"}
)
public class CouponDataSourceConfig {

    @Bean(name = "couponDataSource")
    @ConfigurationProperties(prefix = "spring.coupon.datasource")
    public DataSource couponDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "couponEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(couponDataSource())
            .packages("com.coding.supermarket.domain.coupon") // 设置实体类所在位置
            .persistenceUnit("couponPersistenceUnit").build();
    }

    @Bean(name = "couponTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("couponEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
