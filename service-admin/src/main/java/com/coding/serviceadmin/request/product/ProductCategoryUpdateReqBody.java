package com.coding.serviceadmin.request.product;

import com.coding.commons.base.ReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCategoryUpdateReqBody implements ReqBody {
    private String name;

    private String icon;

    private Integer weight;

    private Long parentId;

    private Long updatedBy;
}
