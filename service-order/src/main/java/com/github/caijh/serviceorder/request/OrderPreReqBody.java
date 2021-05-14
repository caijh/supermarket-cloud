package com.github.caijh.serviceorder.request;

import java.util.List;

import com.coding.commons.base.ReqBody;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderPreReqBody implements ReqBody {

    private List<ProductToBuy> products;


}
