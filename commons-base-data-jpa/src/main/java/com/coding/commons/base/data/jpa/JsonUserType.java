package com.coding.commons.base.data.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import com.vladmihalcea.hibernate.type.util.ReflectionUtils;
import org.hibernate.HibernateException;
import org.hibernate.annotations.common.reflection.XProperty;
import org.hibernate.annotations.common.reflection.java.JavaXMember;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.DynamicParameterizedType;
import org.hibernate.usertype.UserType;

import static com.alibaba.fastjson.parser.Feature.SupportAutoType;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName;

/**
 * 自定义hibernate user type.
 *
 * @deprecated use vladmihalcea instead.
 */
@Deprecated
public class JsonUserType implements UserType, DynamicParameterizedType {

    private Type type;

    @Override
    public void setParameterValues(Properties parameters) {
        final XProperty xProperty = (XProperty) parameters.get(DynamicParameterizedType.XPROPERTY);
        if (xProperty instanceof JavaXMember) {
            type = ReflectionUtils.invokeGetter(xProperty, "javaType");
        } else {
            type = ((ParameterType) parameters.get(PARAMETER_TYPE)).getReturnedClass();
        }
    }

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.OTHER};
    }

    @Override
    public Class<?> returnedClass() {
        return typeToClass();
    }

    @Override
    public boolean equals(Object x, Object y) {
        return x.equals(y);
    }

    @Override
    public int hashCode(Object x) {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings,
                              SharedSessionContractImplementor sharedSessionContractImplementor, Object o)
        throws SQLException {
        String columnValue = resultSet.getString(strings[0]);

        if (columnValue == null) {
            return null;
        }

        try {
            return JSON.parseObject(columnValue.getBytes(StandardCharsets.UTF_8), returnedClass(),
                SupportAutoType);
        } catch (Exception e) {
            throw new HibernateException(
                "Failed to convert String to " + returnedClass() + e.getMessage(), e);
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i,
                            SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {
        if (o == null) {
            preparedStatement.setNull(i, Types.NULL);
        } else {
            try {
                String s = JSON.toJSONString(o, WriteClassName);
                preparedStatement.setObject(i, s, sqlTypes()[0]);
            } catch (Exception e) {
                throw new HibernateException(
                    "Failed to convert " + returnedClass() + " to String " + e.getMessage(), e);
            }
        }
    }

    @Override
    public Object deepCopy(Object value) {
        if (value != null) {
            try {
                return JSON.parseObject(JSON.toJSONString(value), returnedClass(), SupportAutoType);
            } catch (Exception e) {
                throw new HibernateException("Failed to deep copy object", e);
            }
        }
        return null;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object value) {
        try {
            return JSON.toJSONString(value, WriteClassName);
        } catch (Exception e) {
            throw new HibernateException("Failed to disassemble object", e);
        }
    }

    @Override
    public Object assemble(Serializable cached, Object owner) {
        return deepCopy(cached);
    }

    @Override
    public Object replace(Object original, Object target, Object owner) {
        return deepCopy(original);
    }

    private Class typeToClass() {
        Type classType = type;
        if (type instanceof ParameterizedType) {
            classType = ((ParameterizedType) type).getRawType();
        }
        return (Class) classType;
    }
}
