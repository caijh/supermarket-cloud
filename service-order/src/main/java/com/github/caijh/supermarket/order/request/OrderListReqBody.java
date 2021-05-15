package com.github.caijh.supermarket.order.request;

import com.github.caijh.framework.core.model.AbstractListReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderListReqBody extends AbstractListReqBody {

    private Long userId;

}
