package com.coding.supermarket.domain.product.model;

import java.io.Serializable;

import com.coding.commons.domain.country.model.Country;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

/**
 * 产地.
 */
@Getter
@Setter
public class ProductOrigin implements Serializable {

    @Type(type = "jsonb")
    private Country country;

    private String address;

}
