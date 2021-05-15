package com.github.caijh.supermarket.order.request;

import java.util.List;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;


@Data
public class OrderPreReqBody implements ReqBody {

    private List<ProductToBuy> products;


}
