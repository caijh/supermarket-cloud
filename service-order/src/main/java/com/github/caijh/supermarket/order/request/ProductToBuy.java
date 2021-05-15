package com.github.caijh.supermarket.order.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductToBuy {

    private Long productSkuId;

    private Integer price;

    private Integer num;

}
