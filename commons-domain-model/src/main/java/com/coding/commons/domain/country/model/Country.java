package com.coding.commons.domain.country.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.coding.commons.base.PersistentObject;
import lombok.Getter;
import lombok.Setter;

/**
 * 国家,将国家的电话代码作为id.
 */
@Getter
@Setter
@Entity
public class Country implements PersistentObject<Long> {

    @Id
    private Long id;

    private String name;

    private String enName;

    private String fullEnName;

    private String firstChar;

    private String abbreviation;
}
