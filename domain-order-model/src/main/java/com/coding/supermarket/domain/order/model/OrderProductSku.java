package com.coding.supermarket.domain.order.model;

import java.math.BigDecimal;
import java.util.List;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import com.coding.supermarket.domain.product.model.ProductSkuAttr;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
public class OrderProductSku extends JpaBaseEntity<Long> {

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
