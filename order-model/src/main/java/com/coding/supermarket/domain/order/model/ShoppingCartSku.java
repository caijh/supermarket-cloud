package com.coding.supermarket.domain.order.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;

import com.github.caijh.supermarket.base.model.AbstractBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 购物车.
 */
@Builder
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartSku extends AbstractBaseEntity<Long> {

    public static final int MAX_NUM = 100;
    private static final long serialVersionUID = 2887790103029556207L;

    private Long userId;

    private Long productSkuId;

    private Integer num;

    /**
     * 加入购物车时的价格.
     */
    private BigDecimal price;

    private Date createTime;
    private Date updateTime;

}
