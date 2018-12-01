package com.coding.commons.base.data.jpa;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.coding.commons.base.PersistentObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@MappedSuperclass
public abstract class JpaBaseEntity<T extends Serializable> implements PersistentObject<T> {

    @GenericGenerator(
        name = "sequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
            @Parameter(name = "sequence_name", value = "SEQUENCE"),
            @Parameter(name = "initial_value", value = "1000000"),
            @Parameter(name = "increment_size", value = "1")
        }
    )

    @Id
    @GeneratedValue(generator = "sequenceGenerator")
    private T id;

    @Override
    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
