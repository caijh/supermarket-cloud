package com.coding.serviceadmin.request.product;

import java.util.List;

import com.coding.commons.base.ReqBody;
import com.coding.supermarket.domain.product.model.ProductSku;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAddReqBody implements ReqBody {
    private String name;

    private Long categoryId;

    private Long brandId;

    private List<String> thumbnails;

    private String description;

    private List<ProductSku> skuList;
}
