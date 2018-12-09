package com.coding.supermarket.domain.product.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSku {

    private Long id;

    private String thumbnail;

    private List<ProductSkuAttr> skuAttrs;

    private Integer price;

    private Integer referPrice;

    private ProductOrigin origin;

    private String barcode;

    private Integer stockNum;

    private Integer frozenNum;

    private Integer soldNum;

}
