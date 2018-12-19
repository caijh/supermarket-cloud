package com.coding.supermarket.serviceadmin.request.brand;

import com.coding.commons.base.AbstractListReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandListReqBody extends AbstractListReqBody {
    private String name;
}
