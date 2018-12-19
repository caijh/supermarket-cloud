package com.coding.supermarket.domain.order.model;

import java.util.List;

import com.coding.commons.base.RespBody;
import com.coding.supermarket.domain.product.model.ProductSku;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreOrder implements RespBody {

    private Long userId;

    private List<ProductSku> productSkuList;

    private Integer amount;

    private Integer payAmount;

}
