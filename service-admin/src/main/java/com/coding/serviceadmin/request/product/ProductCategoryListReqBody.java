package com.coding.serviceadmin.request.product;

import com.coding.commons.base.AbstractListReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCategoryListReqBody extends AbstractListReqBody {
    private String name;
}
