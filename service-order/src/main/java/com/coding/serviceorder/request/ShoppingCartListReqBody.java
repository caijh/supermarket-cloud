package com.coding.serviceorder.request;

import com.coding.commons.base.AbstractListReqBody;
import com.coding.commons.domain.product.model.ShoppingCartSku;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCartListReqBody extends AbstractListReqBody {
    private Long userId;

    @Override
    public Integer getPageNo() {
        return DEFAULT_PAGE_NO;
    }

    @Override
    public Integer getPageSize() {
        return ShoppingCartSku.MAX_NUM;
    }
}
