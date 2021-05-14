package com.github.caijh.supermarket.product.model;

import java.util.Date;
import javax.persistence.Entity;

import com.github.caijh.supermarket.base.model.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户收藏的商品.
 */
@Getter
@Setter
@Entity
public class ProductFavorite extends AbstractBaseEntity<Long> {

    private static final long serialVersionUID = -8020885669428443139L;
    private Long userId;

    private Long productId;

    private Date createTime;

}
