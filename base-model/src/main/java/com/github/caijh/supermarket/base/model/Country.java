package com.github.caijh.supermarket.base.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 国家,将国家的电话代码作为id.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Country extends AbstractBaseEntity<Long> {

    private static final long serialVersionUID = -2816675111684393289L;
    @Id
    private Long id;

    private String name;

    private String enName;

    private String fullEnName;

    private String firstChar;

    private String abbreviation;

}
