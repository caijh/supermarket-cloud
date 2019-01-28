package com.coding.supermarket.domain.product.model;

import com.coding.commons.base.IndexEnum;

public enum ProductStatus implements IndexEnum {
    ON_SALE(0, "下架"),
    OFF_SALE(1, "上架"),
    DELETED(2, "删除");

    private int index;

    private String comment;

    ProductStatus(int index, String comment) {
        this.index = index;
        this.comment = comment;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    public String getComment() {
        return comment;
    }
}
