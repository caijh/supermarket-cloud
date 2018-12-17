package com.coding.supermarket.domain.product.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Getter
@Setter
public class ProductSkuAttr implements Serializable {

    private String name;

    /**
     * sku 属性的填值方式.
     * 0 - input
     * 1 - select
     * 2 - checkbox
     * 3 - radio
     */
    private Integer type;

    /**
     * sku 属性值.
     * type = 0, 用户输入
     * type = 1, ProductAttrLabel.id
     * type = 2, ProductAttrLabel.id,ProductAttrLabel.id,ProductAttrLabel.id
     * type = 3, ProductAttrLabel.id
     */
    private String value;

    @Type(type = "jsonb")
    private List<ProductAttrLabel> attrs;

}
