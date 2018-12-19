package com.coding.supermarket.domain.product.model;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

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
