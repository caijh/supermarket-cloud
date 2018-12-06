package com.coding.serviceproduct.request.product;

import com.coding.commons.base.AbstractListReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductListReqBody extends AbstractListReqBody {

    private Long categoryId;
}
