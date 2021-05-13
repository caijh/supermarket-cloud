package com.github.caijh.supermarket.product.model;

import java.util.List;
import javax.persistence.Entity;

import com.github.caijh.supermarket.model.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

/**
 * 分类下商品的属性.
 */
@Getter
@Setter
@Entity(name = "category_product_attr")
public class CategoryProductAttr extends AbstractBaseEntity<Long> {

    private static final long serialVersionUID = -4054927475075577335L;
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
