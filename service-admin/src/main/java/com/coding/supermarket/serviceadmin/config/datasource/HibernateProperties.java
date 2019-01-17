package com.coding.supermarket.serviceadmin.config.datasource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;

class HibernateProperties {

    private HibernateProperties() {
    }

    public static Map<String, Object> from(JpaProperties jpaProperties) {
        return jpaProperties.getHibernateProperties(getHibernateSettings(jpaProperties.getHibernate()));
    }

    private static HibernateSettings getHibernateSettings(JpaProperties.Hibernate hibernate) {
        HibernateSettings hibernateSettings = new HibernateSettings();
        hibernateSettings.ddlAuto(hibernate::getDdlAuto);
        Collection<HibernatePropertiesCustomizer> customizers = new ArrayList<>();
        customizers.add(hibernateProperties -> {
            if (hibernate.getNaming().getImplicitStrategy() != null) {
                hibernateProperties
                    .put("hibernate.implicit_naming_strategy", hibernate.getNaming().getImplicitStrategy());
            }
            if (hibernate.getNaming().getPhysicalStrategy() != null) {
                hibernateProperties
                    .put("hibernate.physical_naming_strategy", hibernate.getNaming().getPhysicalStrategy());
            }
        });
        customizers.add(hibernateProperties -> {
            hibernateProperties
                .put("hibernate.transaction.jta.platform", JtaConfig.AtomikosJtaPlatform.class.getName());
            hibernateProperties.put("javax.persistence.transactionType", "JTA");
        });
        hibernateSettings.hibernatePropertiesCustomizers(customizers);
        return hibernateSettings;
    }

}
