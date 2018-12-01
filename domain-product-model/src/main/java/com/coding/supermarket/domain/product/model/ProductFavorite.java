package com.coding.supermarket.domain.product.model;

import java.util.Date;
import javax.persistence.Entity;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户收藏的商品.
 */
@Getter
@Setter
@Entity
public class ProductFavorite extends JpaBaseEntity<Long> {
    private Long userId;

    private Long productId;

    private Date createTime;
}
