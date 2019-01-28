package com.coding.supermarket.domain.product.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAttr implements Serializable {

    private String name;

    private List<String> value;

}
