package com.coding.supermarket.domain.express.model;

import com.coding.commons.base.PersistentObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Express implements PersistentObject<String> {

    private String id;

    private String name;

}
