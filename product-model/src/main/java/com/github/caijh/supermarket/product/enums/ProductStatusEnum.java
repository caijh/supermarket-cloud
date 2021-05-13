package com.github.caijh.supermarket.product.enums;


import com.github.caijh.framework.core.enums.IndexEnum;

public enum ProductStatusEnum implements IndexEnum {
    ON_SALE(0, "下架"),
    OFF_SALE(1, "上架"),
    DELETED(2, "删除");

    private final int index;

    private final String description;

    ProductStatusEnum(int index, String description) {
        this.index = index;
        this.description = description;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    public String getDescription() {
        return this.description;
    }
}
