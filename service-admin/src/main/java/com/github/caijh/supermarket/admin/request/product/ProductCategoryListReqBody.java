package com.github.caijh.supermarket.admin.request.product;

import com.github.caijh.framework.core.model.AbstractListReqBody;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductCategoryListReqBody extends AbstractListReqBody {

    private String name;

}
