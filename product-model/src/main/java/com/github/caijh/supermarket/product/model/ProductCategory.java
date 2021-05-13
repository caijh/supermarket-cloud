package com.github.caijh.supermarket.product.model;

import java.util.Date;
import javax.persistence.Entity;

import com.github.caijh.supermarket.model.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品分类.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "product_category")
public class ProductCategory extends AbstractBaseEntity<Long> {

    private static final long serialVersionUID = 2475891823279765079L;
    private String name;

    private String icon;

    private Integer weight;

    /**
     * 分类状态.
     * <p>
     * 0 - 在用; 1 - 删除.
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
