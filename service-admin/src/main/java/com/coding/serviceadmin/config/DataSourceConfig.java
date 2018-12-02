package com.coding.serviceadmin.config;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
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
public class DataSourceConfig {

    @Bean(name = "baseDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.base")
    public DataSource baseDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "productDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.product")
    public DataSource productDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "couponDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.coupon")
    public DataSource couponDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "orderDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.order")
    public DataSource orderDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Configuration
    @EnableTransactionManagement
    @EnableJpaRepositories(
        entityManagerFactoryRef = "baseEntityManagerFactory",
        transactionManagerRef = "baseTransactionManager",
        basePackages = {"com.coding.commons.domain"} // 设置repository所在包
    )
    public static class BaseDataSourceConfig {
        @Inject
        private JpaProperties jpaProperties;

        @Inject
        @Named("baseDataSource")
        private DataSource baseDataSource;

        @Bean(name = "baseEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
            return builder.dataSource(baseDataSource).properties(jpaProperties.getProperties())
                .packages("com.coding.commons.domain") // 设置实体类所在位置
                .persistenceUnit("basePersistenceUnit").build();
        }

        @Bean(name = "baseEntityManager")
        public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
            return entityManagerFactory(builder).getObject().createEntityManager();
        }

        @Bean(name = "baseTransactionManager")
        public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
            return new JpaTransactionManager(entityManagerFactory(builder).getObject());
        }

    }

    @Configuration
    @EnableTransactionManagement
    @EnableJpaRepositories(
        entityManagerFactoryRef = "productEntityManagerFactory",
        transactionManagerRef = "productTransactionManager",
        basePackages = {"com.coding.supermarket.domain.product"} // 设置repository所在包
    )
    public static class ProductDataSourceConfig {
        @Inject
        private JpaProperties jpaProperties;

        @Inject
        @Named("productDataSource")
        private DataSource productDataSource;

        @Bean(name = "productEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
            return builder.dataSource(productDataSource).properties(jpaProperties.getProperties())
                .packages("com.coding.supermarket.domain.product") // 设置实体类所在位置
                .persistenceUnit("productPersistenceUnit").build();
        }

        @Bean(name = "productEntityManager")
        public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
            return entityManagerFactory(builder).getObject().createEntityManager();
        }

        @Bean(name = "productTransactionManager")
        public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
            return new JpaTransactionManager(entityManagerFactory(builder).getObject());
        }

    }

    @Configuration
    @EnableTransactionManagement
    @EnableJpaRepositories(
        entityManagerFactoryRef = "couponEntityManagerFactory",
        transactionManagerRef = "couponTransactionManager",
        basePackages = {"com.coding.supermarket.domain.coupon"}
    )
    public static class CouponDataSourceConfig {
        @Inject
        private JpaProperties jpaProperties;

        @Inject
        @Named("couponDataSource")
        private DataSource couponDataSource;

        @Bean(name = "couponEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
            return builder.dataSource(couponDataSource).properties(jpaProperties.getProperties())
                .packages("com.coding.supermarket.domain.coupon") // 设置实体类所在位置
                .persistenceUnit("couponPersistenceUnit").build();
        }

        @Bean(name = "couponEntityManager")
        public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
            return entityManagerFactory(builder).getObject().createEntityManager();
        }

        @Bean(name = "couponTransactionManager")
        public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
            return new JpaTransactionManager(entityManagerFactory(builder).getObject());
        }
    }

    @Configuration
    @EnableTransactionManagement
    @EnableJpaRepositories(
        entityManagerFactoryRef = "orderEntityManagerFactory",
        transactionManagerRef = "orderTransactionManager",
        basePackages = {"com.coding.supermarket.domain.order"}
    )
    public static class OrderDataSourceConfig {
        @Inject
        private JpaProperties jpaProperties;

        @Inject
        @Named("orderDataSource")
        private DataSource orderDataSource;

        @Bean(name = "orderEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
            return builder.dataSource(orderDataSource).properties(jpaProperties.getProperties())
                .packages("com.coding.supermarket.domain.order") // 设置实体类所在位置
                .persistenceUnit("orderPersistenceUnit").build();
        }

        @Bean(name = "orderEntityManager")
        public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
            return entityManagerFactory(builder).getObject().createEntityManager();
        }

        @Bean(name = "orderTransactionManager")
        public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
            return new JpaTransactionManager(entityManagerFactory(builder).getObject());
        }

    }
}
