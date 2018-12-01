package com.coding.commons.base.data.jpa;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL95Dialect;

public class CustomPostgreSQLDialect extends PostgreSQL95Dialect {

    public CustomPostgreSQLDialect() {
        super();
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
        this.registerColumnType(Types.OTHER, "jsonb");
    }

}
