package com.coding.serviceadmin.request.shop;

import com.coding.commons.base.AbstractListReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopListReqBody extends AbstractListReqBody {
    private String name;
}
