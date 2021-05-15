package com.github.caijh.supermarket.admin.request.order;

import com.github.caijh.framework.core.model.AbstractListReqBody;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderListReqBody extends AbstractListReqBody {

    private String no;

}
