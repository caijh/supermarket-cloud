package com.github.caijh.supermarket.order.request;

import java.util.List;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;

@Data
public class OrderAddReqBody implements ReqBody {

    private Long userId;

    private List<ProductToBuy> products;

    private Integer payAmount;

}
