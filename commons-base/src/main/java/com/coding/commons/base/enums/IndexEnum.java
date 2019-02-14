package com.coding.commons.base.enums;

import java.util.NoSuchElementException;

public interface IndexEnum {

    /**
     * Get IndexEnum by index.
     *
     * @param index     index
     * @param typeClass enum implements IndexEnum
     * @param <T>       IndexEnum
     * @return T
     */
    static <T extends IndexEnum> T valueOf(int index, Class<T> typeClass) {
        for (T t : typeClass.getEnumConstants()) {
            if (t.getIndex() == index) {
                return t;
            }
        }
        throw new NoSuchElementException();
    }

    int getIndex();
}
