package com.coding.serviceadmin.request.product;

import com.coding.commons.base.ReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAttrLabelAddReqBody implements ReqBody {
    private String name;

    private String label;
}
