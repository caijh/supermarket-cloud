package com.coding.supermarket.domain.product.model;

import java.nio.charset.StandardCharsets;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.coding.commons.base.PersistentObject;
import com.coding.commons.util.Base64Utils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "product_attr_label")
public class ProductAttrLabel implements PersistentObject<String> {
    @Id
    private String id;

    /**
     * 所属类目id.
     */
    private Long categoryId;

    /**
     * 颜色等.
     */
    private String name;

    /**
     * 红色等.
     */
    private String label;

    @Override
    public String getId() {
        return Base64Utils.encrypt((categoryId + name + label).getBytes(StandardCharsets.UTF_8));
    }
}
