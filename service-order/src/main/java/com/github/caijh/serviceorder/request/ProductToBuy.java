package com.github.caijh.serviceorder.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductToBuy {

    private Long productSkuId;

    private Integer price;

    private Integer num;

}
