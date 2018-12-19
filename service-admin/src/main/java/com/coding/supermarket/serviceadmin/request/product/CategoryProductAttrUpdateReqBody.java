package com.coding.supermarket.serviceadmin.request.product;

import java.util.List;

import com.coding.commons.base.ReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryProductAttrUpdateReqBody implements ReqBody {
    private String id;

    private String name;

    private Integer type;

    private List<String> values;
}
