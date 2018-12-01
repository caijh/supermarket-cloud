package com.coding.commons.domain.area.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.coding.commons.base.PersistentObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Area implements PersistentObject<String> {
    @Id
    private String code;

    private String name;

    private String parentCode;

    private Long countryId;

    @Override
    public String getId() {
        return code;
    }
}