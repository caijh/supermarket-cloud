package com.coding.commons.domain.user.enums;

public enum UserGenderEnum {
    FEMALE((byte) 1),
    MALE((byte) 2);

    private byte index;

    UserGenderEnum(byte index) {
        this.index = index;
    }

    public byte getIndex() {
        return index;
    }
}
