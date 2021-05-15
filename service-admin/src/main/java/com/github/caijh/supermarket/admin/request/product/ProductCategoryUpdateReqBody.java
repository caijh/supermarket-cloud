package com.github.caijh.supermarket.admin.request.product;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;

@Data
public class ProductCategoryUpdateReqBody implements ReqBody {

    private String name;

    private String icon;

    private Integer weight;

    private Long parentId;

    private Long updatedBy;

}
