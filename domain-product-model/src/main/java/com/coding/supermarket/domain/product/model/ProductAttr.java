package com.coding.supermarket.domain.product.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAttr implements Serializable {

    private String name;

    /**
     * 如有
     */
    private String value;

}
