package com.coding.supermarket.domain.product.model;

import java.util.Date;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import javax.persistence.Entity;
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

    /**
     * 从第一级分类到这个分类路径, 分类1_分类2_分类3.
     */

    private String path;

    private Long createdBy;

    private Date createTime;

    private Long updatedBy;

    private Date updateTime;

}
