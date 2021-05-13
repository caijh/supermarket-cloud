package com.github.caijh.supermarket.model;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.github.caijh.framework.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = false)
public class AbstractBaseEntity<T extends Serializable> extends BaseEntity<T> {

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

}


