package com.coding.commons.domain.user.enums;

import com.coding.commons.base.IndexEnum;

public enum UserGradeEnum implements IndexEnum {
    GENERAL(0, "普通用户"),
    BRONZE(1, "铜牌"),
    SILVER(2, "银牌"),
    GOLDEN(3, "金牌");

    private int index;

    private String comment;

    UserGradeEnum(int index, String comment) {
        this.index = index;
        this.comment = comment;
    }

    @Override
    public int getIndex() {
        return index;
    }

    public String getComment() {
        return comment;
    }
}
