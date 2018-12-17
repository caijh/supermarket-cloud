package com.coding.supermarket.domain.product.model;

import java.util.Date;
import javax.persistence.Entity;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 商品分类.
 */
@Getter
@Setter
@Entity(name = "product_category")
public class Category extends JpaBaseEntity<Long> {

    private String name;

    private String icon;

    private Integer weight;

    /**
     * 分类状态.
     *
     * @see com.coding.commons.base.CommonStatus
     */
    private Integer status;

    /**
     * 上一级分类.
     */
    private Long parentId;

    private Long createdBy;

    private Date createTime;

    private Long updatedBy;

    private Date updateTime;

}
