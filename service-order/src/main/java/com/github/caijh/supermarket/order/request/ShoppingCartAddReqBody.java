package com.github.caijh.supermarket.order.request;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;

@Data
public class ShoppingCartAddReqBody implements ReqBody {

    private Long userId;

    private Long productSkuId;

}
