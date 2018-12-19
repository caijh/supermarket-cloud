package com.coding.supermarket.serviceadmin.request.product;

import java.util.List;

import com.coding.commons.base.ReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryProductAttrAddReqBody implements ReqBody {
    private Long categoryId;
    private String name;
    private Integer type;
    private List<String> values;
}
