package com.coding.supermarket.domain.product.model;

import com.coding.commons.base.IndexEnum;

public enum ProductStatus implements IndexEnum {
    ON_SALE(0),
    OFF_SALE(1),
    DELETED(2);

    private int index;

    ProductStatus(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return this.index;
    }
}
