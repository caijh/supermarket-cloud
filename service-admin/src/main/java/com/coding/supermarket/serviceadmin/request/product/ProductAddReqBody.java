package com.coding.supermarket.serviceadmin.request.product;

import java.util.List;

import com.coding.commons.base.ReqBody;
import com.coding.supermarket.domain.product.model.ProductBaseAttr;
import com.coding.supermarket.domain.product.model.ProductOrigin;
import com.coding.supermarket.domain.product.model.ProductSku;
import com.coding.supermarket.domain.product.model.ProductSkuAttr;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAddReqBody implements ReqBody {

    private String name;

    private String briefs;

    private Long categoryId;

    private String brandId;

    private List<String> thumbnails;

    private String description;

    private Float taxRate;

    private ProductOrigin origin;

    private List<ProductBaseAttr> baseAttrs;

    private List<ProductSkuAttr> skuAttrs;

    private Long shopId;

    private List<ProductSku> skuList;

}
