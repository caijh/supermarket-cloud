package com.github.caijh.supermarket.product.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAttr implements Serializable {

    private static final long serialVersionUID = 433151691602870912L;
    private String name;

    private List<String> value;

}
