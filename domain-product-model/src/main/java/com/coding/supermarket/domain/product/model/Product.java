package com.coding.supermarket.domain.product.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

/**
 * 商品.
 */
@Getter
@Setter
@Entity
public class Product extends JpaBaseEntity<Long> {
    private String name;

    /**
     * 简介摘要.
     */
    private String briefs;

    /**
     * 所属产品分类id.
     *
     * @see Category
     */
    private Long categoryId;

    /**
     * 所属品牌.
     */
    private Long brandId;

    @Type(type = "jsonb")
    private List<String> thumbnails;

    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * 税率.
     */
    private Float taxRate;

    /**
     * 商品状态.
     *
     * @see ProductStatus
     */
    private Integer status;

    /**
     * 商品的sku list.
     */
    @Transient
    private List<ProductSku> skuList;

    /**
     * 商品所属的门店.
     */
    private Long shopId;

    private Long createdBy;
    private Date createTime;
    private Long updatedBy;
    private Date updateTime;
}
