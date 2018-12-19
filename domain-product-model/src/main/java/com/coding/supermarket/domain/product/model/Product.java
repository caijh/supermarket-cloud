package com.coding.supermarket.domain.product.model;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

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
     *
     * @see Brand
     */
    private Long brandId;

    /**
     * 图册.
     */
    @Type(type = "jsonb")
    private List<String> thumbnails;

    /**
     * 详情.
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * 税率.
     */
    private Float taxRate;

    /**
     * 产地.
     */
    @Type(type = "jsonb")
    private ProductOrigin origin;

    /**
     * 规格参数.
     */
    @Type(type = "jsonb")
    private List<ProductBaseAttr> baseAttrs;

    @Type(type = "jsonb")
    private List<ProductSkuAttr> skuAttrs;

    /**
     * 商品的sku list.
     */
    @Transient
    private List<ProductSku> skuList;

    /**
     * 商品所属的门店.
     */
    private Long shopId;

    /**
     * 商品状态.
     *
     * @see ProductStatus
     */
    private Integer status;

    private Long createdBy;

    private Date createTime;

    private Long updatedBy;

    private Date updateTime;

}
