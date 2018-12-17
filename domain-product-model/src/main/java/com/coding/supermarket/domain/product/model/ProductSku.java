package com.coding.supermarket.domain.product.model;

import java.util.Date;
import javax.persistence.Entity;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Builder
@Getter
@Setter
@Entity
public class ProductSku extends JpaBaseEntity<Long> {
    /**
     * 商品id.
     */
    private Long productId;

    /**
     * sku主图.
     */
    private String thumbnail;

    /**
     * sku 属性.
     */
    @Type(type = "jsonb")
    private ProductSkuAttr skuAttr;

    /**
     * 价格,单位分.
     */
    private Integer price;

    /**
     * 参考价, 单位分.
     */
    private Integer referPrice;

    /**
     * 产地.
     */
    @Type(type = "jsonb")
    private ProductOrigin origin;

    /**
     * 条形码.
     */
    private String barcode;

    /**
     * sku商品状态.
     *
     * @see ProductStatus
     */
    private Integer status;

    private Long createdBy;
    private Date createTime;
    private Long updatedBy;
    private Date updateTime;
}
