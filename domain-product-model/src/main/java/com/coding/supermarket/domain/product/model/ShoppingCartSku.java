package com.coding.supermarket.domain.product.model;

import java.math.BigDecimal;
import java.util.Date;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartSku extends JpaBaseEntity<Long> {

    public static final int MAX_NUM = 100;

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
