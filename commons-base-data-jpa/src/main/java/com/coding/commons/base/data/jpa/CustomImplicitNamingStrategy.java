package com.coding.commons.base.data.jpa;

import com.coding.commons.util.ReflectUtils;
import org.hibernate.annotations.Immutable;
import org.hibernate.boot.model.naming.EntityNaming;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl;

public class CustomImplicitNamingStrategy extends ImplicitNamingStrategyLegacyJpaImpl {

    private static final String TABLE_PREFIX = "t_";
    private static final String VIEW_PREFIX = "v_";

    @Override
    protected String transformEntityName(EntityNaming entityNaming) {
        String prefix = TABLE_PREFIX;

        String className = entityNaming.getClassName();
        Class<?> classType = ReflectUtils.getClass(className);
        Immutable annotation = classType.getAnnotation(Immutable.class);
        if (annotation != null) {
            prefix = VIEW_PREFIX;
        }

        return prefix + super.transformEntityName(entityNaming);
    }
}
