package com.coding.supermarket.domain.order.model;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;

import com.github.caijh.supermarket.base.model.AbstractBaseEntity;
import com.github.caijh.supermarket.product.model.ProductSkuAttr;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderProductSku extends AbstractBaseEntity<Long> {

    private static final long serialVersionUID = 3742969265744780275L;
    private Long orderId;
    private Long productSkuId;
    private Long productId;
    @Type(
        type = "jsonb"
    )
    private List<ProductSkuAttr> skuAttr;
    private String barcode;
    private String thumbnail;
    private BigDecimal price;
    private BigDecimal referPrice;

    /**
     * 购买数量.
     */
    private Integer buyNum;

    @Type(type = "jsonb")
    private OrderProduct orderProduct;

}
