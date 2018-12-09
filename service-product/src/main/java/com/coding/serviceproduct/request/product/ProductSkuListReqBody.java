package com.coding.serviceproduct.request.product;

import java.util.List;

import com.coding.commons.base.ReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSkuListReqBody implements ReqBody {
    private List<Long> productSkuIds;
}
