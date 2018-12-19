package com.coding.supermarket.serviceadmin.config.datasource;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;

class HibernateSettingsFactory {

    private HibernateSettingsFactory() {
    }

    static HibernateSettings getHibernateSettings(JpaProperties jpaProperties) {
        HibernateSettings hibernateSettings = new HibernateSettings();
        hibernateSettings.ddlAuto(() -> jpaProperties.getHibernate().getDdlAuto());
        Collection<HibernatePropertiesCustomizer> customizers = new ArrayList<>();
        customizers.add(hibernateProperties -> {
            if (jpaProperties.getHibernate().getNaming().getImplicitStrategy() != null) {
                hibernateProperties.put("hibernate.implicit_naming_strategy",
                    jpaProperties.getHibernate().getNaming().getImplicitStrategy());
            }
            if (jpaProperties.getHibernate().getNaming().getPhysicalStrategy() != null) {
                hibernateProperties.put("hibernate.physical_naming_strategy", jpaProperties.getHibernate().getNaming().getPhysicalStrategy());
            }
        });
        hibernateSettings.hibernatePropertiesCustomizers(customizers);
        return hibernateSettings;
    }
}
