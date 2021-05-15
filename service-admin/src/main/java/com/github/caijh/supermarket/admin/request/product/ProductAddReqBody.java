package com.github.caijh.supermarket.admin.request.product;

import java.util.List;

import com.github.caijh.framework.core.model.ReqBody;
import com.github.caijh.supermarket.product.model.ProductBaseAttr;
import com.github.caijh.supermarket.product.model.ProductOriginPlace;
import com.github.caijh.supermarket.product.model.ProductSku;
import com.github.caijh.supermarket.product.model.ProductSkuAttr;
import lombok.Data;

@Data
public class ProductAddReqBody implements ReqBody {

    private String name;

    private String briefs;

    private Long categoryId;

    private String brandId;

    private List<String> thumbnails;

    private String description;

    private Float taxRate;

    private ProductOriginPlace origin;

    private List<ProductBaseAttr> baseAttrs;

    private List<ProductSkuAttr> skuAttrs;

    private Long shopId;

    private List<ProductSku> skuList;

}
