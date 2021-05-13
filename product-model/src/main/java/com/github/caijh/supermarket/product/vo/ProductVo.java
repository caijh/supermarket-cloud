package com.github.caijh.supermarket.product.vo;

import java.util.Date;
import java.util.List;

import com.github.caijh.supermarket.product.model.Brand;
import com.github.caijh.supermarket.product.model.ProductBaseAttr;
import com.github.caijh.supermarket.product.model.ProductCategory;
import com.github.caijh.supermarket.product.model.ProductOriginPlace;
import com.github.caijh.supermarket.product.model.ProductSku;
import com.github.caijh.supermarket.product.model.ProductSkuAttr;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductVo {

    private Long id;
    private String name;
    private String briefs;
    private ProductCategory category;
    private Brand brand;
    private List<String> thumbnails;
    private String description;
    private Float taxRate;
    private ProductOriginPlace origin;
    private List<ProductBaseAttr> baseAttrs;
    private List<ProductSkuAttr> skuAttrs;
    private List<ProductSku> skuList;
    private Long shopId;
    private Integer status;
    private Long createdBy;
    private Date createTime;
    private Long updatedBy;
    private Date updateTime;

}
