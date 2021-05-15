package com.github.caijh.supermarket.admin.request.shop;

import com.github.caijh.framework.core.model.AbstractListReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopListReqBody extends AbstractListReqBody {

    private String name;

    private Long userId;

}
