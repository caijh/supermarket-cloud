package com.coding.supermarket.domain.product.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductAttr implements Serializable {

    private String name;

    /**
     * 逗号分割.
     */
    private String value;

}
