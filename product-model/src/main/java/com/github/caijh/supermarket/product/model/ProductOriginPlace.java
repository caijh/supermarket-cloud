package com.github.caijh.supermarket.product.model;

import java.io.Serializable;

import com.github.caijh.supermarket.model.Country;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

/**
 * 产地.
 */
@Getter
@Setter
public class ProductOriginPlace implements Serializable {

    private static final long serialVersionUID = -377360375489480900L;
    @Type(type = "jsonb")
    private Country country;

    private String address;

}
