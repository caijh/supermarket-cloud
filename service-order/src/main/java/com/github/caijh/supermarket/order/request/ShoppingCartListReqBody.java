package com.github.caijh.supermarket.order.request;

import com.github.caijh.framework.core.model.AbstractListReqBody;
import com.github.caijh.supermarket.order.model.ShoppingCartSku;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ShoppingCartListReqBody extends AbstractListReqBody {

    private Long userId;

    @Override
    public Integer getPageNo() {
        return AbstractListReqBody.DEFAULT_PAGE_NO;
    }

    @Override
    public Integer getPageSize() {
        return ShoppingCartSku.MAX_NUM;
    }

}
