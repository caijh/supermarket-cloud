package com.coding.supermarket.domain.product.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import com.coding.supermarket.domain.product.enums.ProductStatusEnum;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@NoArgsConstructor
@AllArgsConstructor
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
     * sku 属性.
     */
    @Type(type = "jsonb")
    private List<ProductSkuAttr> skuAttr;

    /**
     * 条形码.
     */
    private String barcode;

    /**
     * sku主图.
     */
    private String thumbnail;

    /**
     * 价格,单位元.
     */
    private BigDecimal price;

    /**
     * 参考价, 单位元.
     */
    private BigDecimal referPrice;

    /**
     * sku商品状态.
     *
     * @see ProductStatusEnum
     */
    private Integer status;

    private Long createdBy;

    private Date createTime;

    private Long updatedBy;

    private Date updateTime;

}
