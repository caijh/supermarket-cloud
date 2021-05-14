package com.github.caijh.supermarket.base.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.caijh.framework.core.model.PersistentObject;
import lombok.Data;

@Data
@Entity
public class Area implements PersistentObject<String> {

    private static final long serialVersionUID = -3954940912711021043L;

    @Id
    private String code;

    private String name;

    private String parentCode;

    private Long countryId;

    @JsonIgnore
    @Override
    public String getId() {
        return this.code;
    }

}
