package com.coding.supermarket.domain.product.model;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import java.util.List;

/**
 * 分类下商品的属性.
 */
@Getter
@Setter
@Entity(name = "category_product_attr")
public class CategoryProductAttr extends JpaBaseEntity<Long> {

    private Long categoryId;

    private String name;

    /**
     * 属性的填值方式.
     * 0 - input
     * 1 - select
     * 2 - checkbox
     * 3 - radio
     */
    private Integer type;

    /**
     * 可选值.
     */
    @Type(type = "jsonb")
    private List<String> values;

}
