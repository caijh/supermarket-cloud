package com.coding.commons.base;

import java.io.Serializable;

/**
 * PO.
 */
public interface PersistentObject<T extends Serializable> extends Serializable {

    T getId();
}
