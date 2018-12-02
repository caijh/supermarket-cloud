package com.coding.serviceadmin.request.order;

import com.coding.commons.base.AbstractListReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderListReqBody extends AbstractListReqBody {
    private String no;
}
