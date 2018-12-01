package com.coding.supermarket.domain.product.model;

import java.util.Date;
import javax.persistence.Entity;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
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

    private Date createTime;
    private Date updateTime;
}
