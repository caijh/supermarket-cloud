package com.coding.serviceorder.request;

import java.util.List;

import com.coding.commons.base.ReqBody;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderPreReqBody implements ReqBody {

    @Getter
    @Setter
    public static class ProductToBuy {
        private Long productSkuId;
        private Integer price;
        private Integer num;
    }

    private List<ProductToBuy> products;


}
