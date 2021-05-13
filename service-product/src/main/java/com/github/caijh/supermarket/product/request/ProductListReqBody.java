package com.github.caijh.supermarket.product.request;

import com.github.caijh.framework.core.model.AbstractListReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductListReqBody extends AbstractListReqBody {

    private Long categoryId;

}
