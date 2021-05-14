package com.github.caijh.supermarket.base.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.github.caijh.framework.core.model.PersistentObject;
import lombok.Data;

/**
 * 国家,将国家的电话代码作为id.
 */
@Entity
@Data
public class Country implements PersistentObject<Long> {

    private static final long serialVersionUID = -2816675111684393289L;
    @Id
    private Long id;

    private String name;

    private String enName;

    private String fullEnName;

    private String firstChar;

    private String abbreviation;

}
