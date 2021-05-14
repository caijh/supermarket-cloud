package com.github.caijh.supermarket.product.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.caijh.supermarket.base.model.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductSkuExt extends AbstractBaseEntity<Long> {

    private static final long serialVersionUID = 7210303544993587691L;

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

    @JsonIgnore
    @Override
    public Long getId() {
        return this.productSkuId;
    }

}
