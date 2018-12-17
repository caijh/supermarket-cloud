package com.coding.supermarket.domain.product.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.coding.commons.base.PersistentObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProductSkuExt implements PersistentObject<Long> {

    @Id
    private Long productSkuId;

    /**
     * 当前可卖库存.
     */
    private Integer stockNum;

    /**
     * 被冻结了的库存.
     */
    private Integer frozenNum;

    /**
     * 已卖数.
     */
    private Integer soldNum;

    @Override
    public Long getId() {
        return this.productSkuId;
    }

}
