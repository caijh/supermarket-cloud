package com.coding.commons.domain.user.enums;

import com.coding.commons.base.IndexEnum;

public enum UserStatusEnum implements IndexEnum {
    /**
     * 正常的.
     */
    NORMAL(0),
    /**
     * 锁定了.
     */
    LOCKED(1),
    /**
     * 删除了.
     */
    DELETED(2);

    private int index;

    UserStatusEnum(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return this.index;
    }
}
