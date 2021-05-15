package com.github.caijh.supermarket.order.model;

import java.util.List;

import com.github.caijh.framework.core.model.RespBody;
import com.github.caijh.supermarket.product.model.ProductSku;
import lombok.Data;

@Data
public class PreOrder implements RespBody {

    private static final long serialVersionUID = -5637887347190145791L;
    private Long userId;

    private List<ProductSku> productSkuList;

    private Integer amount;

    private Integer payAmount;

}
