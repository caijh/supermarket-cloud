package com.github.caijh.serviceorder.request;

import com.coding.commons.base.ReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCartAddReqBody implements ReqBody {

    private Long userId;

    private Long productSkuId;

}
