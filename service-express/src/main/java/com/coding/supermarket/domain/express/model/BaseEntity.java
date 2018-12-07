package com.coding.supermarket.domain.express.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseEntity<T> {

    private T id;

}
