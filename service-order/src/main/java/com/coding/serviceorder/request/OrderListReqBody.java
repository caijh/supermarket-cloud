package com.coding.serviceorder.request;

import com.coding.commons.base.AbstractListReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderListReqBody extends AbstractListReqBody {
    private Long userId;
}
